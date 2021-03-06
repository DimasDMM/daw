package com.daw.themadridtimes.publicist;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.daw.themadridtimes.ad.Ad;
import com.daw.themadridtimes.ad.AdService;
import com.daw.themadridtimes.ad.AdView;
import com.daw.themadridtimes.files.FileUploadCommons;
import com.daw.themadridtimes.requests.FormModifyAd;
import com.daw.themadridtimes.requests.FormNewAd;
import com.daw.themadridtimes.user.User;
import com.daw.themadridtimes.user.UserService;
import com.daw.themadridtimes.utils.Message;
import com.daw.themadridtimes.utils.ModPagination;
import com.daw.themadridtimes.utils.ModPagination.ModPageItem;
import com.daw.themadridtimes.webconfig.Config;

@Controller
public class PublicistController {
	
	@Autowired
	protected AdService adService;
	
	@Autowired
	protected UserService userService;
	
	@Autowired
	protected Config config;
	

	@RequestMapping(value="/publicista/anuncio/nuevo", method=RequestMethod.GET)
	public ModelAndView showFormNewPreview(Model model) {
		
		model.addAttribute("ad_title", "");
		model.addAttribute("ad_url", "");
		model.addAttribute("ad_weight", "");
		model.addAttribute("ad_lim_date_start", "");
		model.addAttribute("ad_lim_date_end", "");
		model.addAttribute("ad_lim_clicks", "");
		model.addAttribute("ad_lim_views", "");
		
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
		
		User userLogged = userService.getLoggedUser();
		
		Ad ad = new Ad( userLogged, r.getTitle(), r.getUrl(), r.getWeight(), r.getLimdatestart(), r.getLimdateend(), r.getLimclicks(), r.getLimviews() );
		ad = adService.save( ad );
		
		FileUploadCommons.saveImage( file, config.getPathImgAds(), String.valueOf(ad.getId()) );

		model.addAttribute("is_modification", true);

		return new ModelAndView( new RedirectView("/publicista/anuncio/lista/publicado") );
	}
	
	@RequestMapping(value="/publicista/anuncio/{id}", method=RequestMethod.GET)
	public ModelAndView showFormModify(Model model, @PathVariable long id) {
		Message message;
		Ad ad = adService.get(id);
		
		if(ad == null) {
			message = new Message(1, "El anuncio no existe. Por favor, seleccione uno de la lista.", "danger");
			model.addAttribute("message", message);
			return showList(model, 1);
		}
		
		AdView adv = new AdView(ad);
		
		model.addAttribute("ad_title", adv.getTitle());
		model.addAttribute("ad_url", adv.getUrl());
		model.addAttribute("ad_weight", adv.getWeight());
		model.addAttribute("ad_lim_date_start", adv.getLimDateStartStr());
		model.addAttribute("ad_lim_date_end", adv.getLimDateEndStr());
		model.addAttribute("ad_lim_clicks", adv.getLimClicksStr());
		model.addAttribute("ad_lim_views", adv.getLimViewsStr());
		
		model.addAttribute("is_modification", true);
		
		return new ModelAndView("ads_form");
	}
	
	@RequestMapping(value="/publicista/anuncio/{id}", method=RequestMethod.POST)
	public ModelAndView showFormModify(Model model, FormModifyAd r, @PathVariable long id, @RequestParam("file") MultipartFile file) {
		Message message;
		Ad ad = adService.get(id);
		
		if(ad == null) {
			message = new Message(1, "El anuncio no existe. Por favor, seleccione uno de la lista.", "danger");
			model.addAttribute("message", message);
			return showList(model, 1);
		}
		
		message = r.validation();
		if(message.getCode() != 0) {
			model.addAttribute("message", message);
			return showFormModify(model, id);
		}
		
		ad.setTitle(r.getTitle());
		ad.setUrl(r.getUrl());
		ad.setWeight(r.getWeight());
		ad.setLimDateStart(r.getLimdatestart());
		ad.setLimDateEnd(r.getLimdateend());
		ad.setLimClicks(r.getLimclicks());
		ad.setLimViews(r.getLimviews());
		
		ad = adService.save(ad);
		
		FileUploadCommons.saveImage( file, config.getPathImgAds(), String.valueOf(ad.getId()) );

		return new ModelAndView( new RedirectView("/publicista/anuncio/lista/publicado") );
	}
	
	@RequestMapping(value="/publicista/anuncio/{id}/eliminar", method=RequestMethod.GET)
	public ModelAndView deleteAd(Model model, @PathVariable long id) {
		Ad ad = adService.get(id);
		adService.delete(ad);
		return new ModelAndView( new RedirectView("/publicista/anuncio/lista/eliminado") );
	}
	
	@RequestMapping(value="/publicista/anuncio/lista", method=RequestMethod.GET)
	public ModelAndView showList(Model model, @RequestParam(required=false) Integer page) {
		if(page == null || page.intValue() < 0) {
			page = 0;
		} else {
			page--;
		}
		
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
	
	private ModelAndView showListAux(Model model, int page) {
		Page<Ad> p = adService.listWhenPermission(page);
		
		List<Ad> adList = p.getContent();
		model.addAttribute("ad_list", AdView.castList(adList) );
		
		ModPagination modPagination = new ModPagination();
		List<ModPageItem> pageList = modPagination.getModPageList(p, "/publicista/anuncio/lista?page=");
		model.addAttribute("page_list", pageList);
		
		return new ModelAndView("ads_list");
	}
}
