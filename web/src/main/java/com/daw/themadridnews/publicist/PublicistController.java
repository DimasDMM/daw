package com.daw.themadridnews.publicist;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.daw.themadridnews.ad.Ad;
import com.daw.themadridnews.ad.AdRepository;
import com.daw.themadridnews.ad.AdView;
import com.daw.themadridnews.files.FileUploadService;
import com.daw.themadridnews.requests.FormModifyAd;
import com.daw.themadridnews.requests.FormNewAd;
import com.daw.themadridnews.user.User;
import com.daw.themadridnews.user.UserComponent;
import com.daw.themadridnews.utils.Message;
import com.daw.themadridnews.utils.ModPagination;
import com.daw.themadridnews.utils.ModPagination.ModPageItem;
import com.daw.themadridnews.webconfig.Config;

@Controller
public class PublicistController {
	
	@Autowired
	protected AdRepository adRepository;
	
	@Autowired
	protected UserComponent userComponent;
	
	@Autowired
	protected Config config;
	
	protected static final int nItemsList = 20;
	

	@RequestMapping(value="/publicista/anuncio/nuevo", method=RequestMethod.GET)
	public ModelAndView showFormNewPreview(Model model) {
		
		model.addAttribute("ad_title", "");
		model.addAttribute("ad_url", "");
		model.addAttribute("ad_type_banner", false);
		model.addAttribute("ad_type_background", false);
		model.addAttribute("ad_weight", "");
		model.addAttribute("ad_date_start", "");
		model.addAttribute("ad_date_end", "");
		model.addAttribute("ad_clicks", "");
		model.addAttribute("ad_views", "");
		
		model.addAttribute("is_modification", false);
		
		return new ModelAndView("ads_form");
	}
	
	@RequestMapping(value="/publicista/anuncio/nuevo", method=RequestMethod.POST)
	public ModelAndView showFormNewPreview(Model model, FormNewAd r, @RequestParam("file") MultipartFile file) {
		Message message = r.validation();
		if(message.getCode() != 0) {
			model.addAttribute("message", message);
			return showFormNewPreview(model);
		}
		
		User userLogged = userComponent.getLoggedUser();
		
		Ad ad = new Ad( userLogged, r.getTitle(), r.getUrl(), r.getType(), r.getWeight(), r.getDatestart(), r.getDateend(), r.getClicks(), r.getViews() );
		ad = adRepository.save( ad );
		
		FileUploadService.saveImage( file, config.getPathImgAds(), String.valueOf(ad.getId()) );

		model.addAttribute("is_modification", true);

		return new ModelAndView( new RedirectView("/publicista/anuncio/lista/publicado") );
	}
	
	@RequestMapping(value="/publicista/anuncio/{id}", method=RequestMethod.GET)
	public ModelAndView showFormModify(Model model, @PathVariable long id) {
		Message message;
		Ad ad = adRepository.findOne(id);
		
		if(ad == null) {
			message = new Message(1, "El anuncio no existe. Por favor, seleccione uno de la lista.", "danger");
			model.addAttribute("message", message);
			return showList(model);
		}
		
		AdView adv = new AdView(ad);
		
		model.addAttribute("ad_title", adv.getTitle());
		model.addAttribute("ad_url", adv.getUrl());
		model.addAttribute("ad_type_banner", (adv.getType()==0) );
		model.addAttribute("ad_type_background", (adv.getType()==1) );
		model.addAttribute("ad_weight", adv.getWeight());
		model.addAttribute("ad_date_start", adv.getLimDateStartStr());
		model.addAttribute("ad_date_end", adv.getLimDateEndStr());
		model.addAttribute("ad_clicks", adv.getLimClicksStr());
		model.addAttribute("ad_views", adv.getLimViewsStr());
		
		model.addAttribute("is_modification", true);
		
		return new ModelAndView("ads_form");
	}
	
	@RequestMapping(value="/publicista/anuncio/{id}", method=RequestMethod.POST)
	public ModelAndView showFormModify(Model model, FormModifyAd r, @PathVariable long id, @RequestParam("file") MultipartFile file) {
		Message message;
		Ad ad = adRepository.findOne(id);
		
		if(ad == null) {
			message = new Message(1, "El anuncio no existe. Por favor, seleccione uno de la lista.", "danger");
			model.addAttribute("message", message);
			return showList(model);
		}
		
		message = r.validation();
		if(message.getCode() != 0) {
			model.addAttribute("message", message);
			return showFormModify(model, id);
		}
		
		ad.setTitle(r.getTitle());
		ad.setUrl(r.getUrl());
		ad.setType(r.getType());
		ad.setWeight(r.getWeight());
		ad.setLimDateStart(r.getDatestart());
		ad.setLimDateEnd(r.getDateend());
		ad.setLimClicks(r.getClicks());
		ad.setLimViews(r.getViews());
		
		ad = adRepository.save(ad);
		
		FileUploadService.saveImage( file, config.getPathImgAds(), String.valueOf(ad.getId()) );

		return new ModelAndView( new RedirectView("/publicista/anuncio/lista/publicado") );
	}
	
	@RequestMapping(value="/publicista/anuncio/{id}/eliminar", method=RequestMethod.GET)
	public ModelAndView deleteAd(Model model, @PathVariable long id) {
		adRepository.delete(id);
		return new ModelAndView( new RedirectView("/publicista/anuncio/lista/eliminado") );
	}
	
	@RequestMapping(value="/publicista/anuncio/lista", method=RequestMethod.GET)
	public ModelAndView showList(Model model) {
		return showListAux(model, 0);
	}
	
	@RequestMapping(value="/publicista/anuncio/lista/publicado", method=RequestMethod.GET)
	public ModelAndView showListPublished(Model model) {
		Message message = new Message(0, "El anuncio ha sido publicado correctamente", "success");
		model.addAttribute("message", message);
		return showListAux(model, 0);
	}
	
	@RequestMapping(value="/publicista/anuncio/lista/eliminado", method=RequestMethod.GET)
	public ModelAndView showListDeleted(Model model) {
		Message message = new Message(0, "El anuncio ha sido eliminado correctamente", "success");
		model.addAttribute("message", message);
		return showListAux(model, 0);
	}
	
	@RequestMapping(value="/publicista/anuncio/lista/{nPage}", method=RequestMethod.GET)
	public ModelAndView showList(Model model, @PathVariable int nPage) {
		return showListAux(model, nPage-1);
	}
	
	private ModelAndView showListAux(Model model, int nPage) {
		Page<Ad> page;

		if(userComponent.hasRole("ADMIN")) {
			page = adRepository.findAll( new PageRequest(nPage, nItemsList, Sort.Direction.DESC, "id") );
		} else {
			User userLogged = userComponent.getLoggedUser();
			page = adRepository.findByAuthor( userLogged, new PageRequest(nPage, nItemsList, Sort.Direction.DESC, "id") );
		}
		
		List<Ad> adList = page.getContent();
		model.addAttribute("ad_list", AdView.castList(adList) );
		
		ModPagination modPagination = new ModPagination();
		List<ModPageItem> pageList = modPagination.getModPageList(page, "/publicista/anuncio/lista/");
		model.addAttribute("page_list", pageList);
		
		return new ModelAndView("ads_list");
	}
}
