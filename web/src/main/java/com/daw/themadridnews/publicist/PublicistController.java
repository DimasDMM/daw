package com.daw.themadridnews.publicist;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.daw.themadridnews.Config;
import com.daw.themadridnews.ad.Ad;
import com.daw.themadridnews.ad.AdRepository;
import com.daw.themadridnews.ad.AdView;
import com.daw.themadridnews.article.ArticleRepository;
import com.daw.themadridnews.comment.CommentRepository;
import com.daw.themadridnews.files.FileUploadService;
import com.daw.themadridnews.requests.FormModifyAd;
import com.daw.themadridnews.requests.FormNewAd;
import com.daw.themadridnews.utils.Message;
import com.daw.themadridnews.utils.ModPagination;
import com.daw.themadridnews.utils.ModPagination.ModPageItem;

@Controller
public class PublicistController {
	
	@Autowired
	protected AdRepository adRepository;
	
	@Autowired
	protected ArticleRepository articleRepository;
	
	@Autowired
	protected CommentRepository commentRepository;
	
	@Autowired Config config;
	
	private static final int nItemsList = 20;
	

	@RequestMapping(value="/publicista/anuncio/nuevo", method=RequestMethod.GET)
	public String showFormNewPreview(Model model, HttpServletRequest request) {
		
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

		config.setPageParams(model, request);
		
		return "ads_form";
	}
	
	@RequestMapping(value="/publicista/anuncio/nuevo", method=RequestMethod.POST)
	public String showFormNewPreview(Model model, FormNewAd r, HttpServletRequest request, @RequestParam("file") MultipartFile file) {
		Message message = r.validation();
		if(message.getCode() != 0) {
			model.addAttribute("message", message);
			return showFormNewPreview(model, request);
		}
		
		Ad ad = new Ad( r.getTitle(), r.getUrl(), r.getType(), r.getWeight(), r.getDatestart(), r.getDateend(), r.getClicks(), r.getViews() );
		ad = adRepository.save( ad );
		
		FileUploadService.saveImage( file, config.getPathImgAds(), String.valueOf(ad.getId()) );

		model.addAttribute("is_modification", true);

		return "redirect:/publicista/anuncio/lista/publicado";
	}
	
	@RequestMapping(value="/publicista/anuncio/{id}", method=RequestMethod.GET)
	public String showFormModify(Model model, @PathVariable long id, HttpServletRequest request) {
		Message message;
		Ad ad = adRepository.findOne(id);
		
		if(ad == null) {
			message = new Message(1, "El anuncio no existe. Por favor, seleccione uno de la lista.", "danger");
			model.addAttribute("message", message);
			return showList(model, request);
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

		config.setPageParams(model, request);
		
		return "ads_form";
	}
	
	@RequestMapping(value="/publicista/anuncio/{id}", method=RequestMethod.POST)
	public String showFormModify(Model model, FormModifyAd r, @PathVariable long id, HttpServletRequest request, @RequestParam("file") MultipartFile file) {
		Message message;
		Ad ad = adRepository.findOne(id);
		
		if(ad == null) {
			message = new Message(1, "El anuncio no existe. Por favor, seleccione uno de la lista.", "danger");
			model.addAttribute("message", message);
			return showList(model, request);
		}
		
		message = r.validation();
		if(message.getCode() != 0) {
			model.addAttribute("message", message);
			return showFormModify(model, id, request);
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
		
		return "redirect:/publicista/anuncio/lista/publicado";
	}
	
	@RequestMapping(value="/publicista/anuncio/{id}/eliminar", method=RequestMethod.GET)
	public String deleteAd(Model model, @PathVariable long id, HttpServletRequest request) {
		adRepository.delete(id);
		return "redirect:/publicista/anuncio/lista/eliminado";
	}
	
	@RequestMapping(value="/publicista/anuncio/lista", method=RequestMethod.GET)
	public String showList(Model model, HttpServletRequest request) {
		return showListAux(model, 0, request);
	}
	
	@RequestMapping(value="/publicista/anuncio/lista/publicado", method=RequestMethod.GET)
	public String showListPublished(Model model, HttpServletRequest request) {
		Message message = new Message(0, "El anuncio ha sido publicado correctamente", "success");
		model.addAttribute("message", message);
		return showListAux(model, 0, request);
	}
	
	@RequestMapping(value="/publicista/anuncio/lista/eliminado", method=RequestMethod.GET)
	public String showListDeleted(Model model, HttpServletRequest request) {
		Message message = new Message(0, "El anuncio ha sido eliminado correctamente", "success");
		model.addAttribute("message", message);
		return showListAux(model, 0, request);
	}
	
	@RequestMapping(value="/publicista/anuncio/lista/{nPage}", method=RequestMethod.GET)
	public String showList(Model model, @PathVariable int nPage, HttpServletRequest request) {
		return showListAux(model, nPage-1, request);
	}
	
	private String showListAux(Model model, int nPage, HttpServletRequest request) {
		Page<Ad> page = adRepository.findAll( new PageRequest(nPage, nItemsList) );
		
		List<Ad> adList = page.getContent();
		model.addAttribute("ad_list", AdView.castList(adList) );
		
		ModPagination modPagination = new ModPagination();
		List<ModPageItem> pageList = modPagination.getModPageList(page, "/publicista/anuncio/lista/");
		model.addAttribute("page_list", pageList);
		
		config.setPageParams(model, request);
		
		return "ads_list";
	}
}
