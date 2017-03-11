package com.daw.themadridnews.publicist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.daw.themadridnews.ad.Ad;
import com.daw.themadridnews.ad.AdRepository;
import com.daw.themadridnews.ad.AdView;
import com.daw.themadridnews.requests.FormModifyAd;
import com.daw.themadridnews.requests.FormNewAd;
import com.daw.themadridnews.utils.Message;
import com.daw.themadridnews.utils.ModPagination;
import com.daw.themadridnews.utils.ModPagination.ModPageItem;

@Controller
public class PublicistController {
	
	@Autowired
	protected AdRepository adRepository;
	
	private static final int nItemsList = 20;
	
	
	@RequestMapping(value="/publicista/anuncio/nuevo", method=RequestMethod.GET)
	public String showFormNewPreview(Model model, FormNewAd r) {

		

		// Guardar

		return "redirect:/publicista/anuncio/lista/publicado";
	}
	
	@RequestMapping(value="/publicista/anuncio/{id}", method=RequestMethod.GET)
	public String showFormModify(Model model, @PathVariable long id) {
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
		
		return "ads_form";
	}
	
	@RequestMapping(value="/publicista/anuncio/{id}", method=RequestMethod.POST)
	public String showFormModify(Model model, FormModifyAd r, @PathVariable long id) {
		Message message;
		Ad ad = adRepository.findOne(id);
		
		if(ad == null) {
			message = new Message(1, "El anuncio no existe. Por favor, seleccione uno de la lista.", "danger");
			model.addAttribute("message", message);
			return showList(model);
		}
		
		message = r.validation();
		if(message.getCode() != 0) {

			
		}

		
		
		ad = adRepository.save(ad);
		
		return "redirect:/publicista/anuncio/publicado";
	}
	
	@RequestMapping(value="/publicista/anuncio/lista", method=RequestMethod.GET)
	public String showList(Model model) {
		return showListAux(model, 0);
	}
	
	@RequestMapping(value="/publicista/anuncio/lista/publicado", method=RequestMethod.GET)
	public String showListPublished(Model model) {
		Message message = new Message(0, "El anuncio ha sido publicado correctamente", "success");
		model.addAttribute("message", message);
		return showListAux(model, 0);
	}
	
	@RequestMapping(value="/publicista/anuncio/lista/ocultado", method=RequestMethod.GET)
	public String showListHidden(Model model) {
		Message message = new Message(0, "El anuncio ha sido ocultado correctamente", "success");
		model.addAttribute("message", message);
		return showListAux(model, 0);
	}
	
	@RequestMapping(value="/publicista/anuncio/lista/eliminado", method=RequestMethod.GET)
	public String showListDeleted(Model model) {
		Message message = new Message(0, "El anuncio ha sido eliminado correctamente", "success");
		model.addAttribute("message", message);
		return showListAux(model, 0);
	}
	
	@RequestMapping(value="/publicista/anuncio/lista/{nPage}", method=RequestMethod.GET)
	public String showList(Model model, @PathVariable int nPage) {
		return showListAux(model, nPage-1);
	}
	
	private String showListAux(Model model, int nPage) {
		Page<Ad> page = adRepository.findAll( new PageRequest(nPage, nItemsList) );
		
		List<Ad> adList = page.getContent();
		model.addAttribute("ad_list", AdView.castList(adList) );
		
		ModPagination modPagination = new ModPagination();
		List<ModPageItem> pageList = modPagination.getModPageList(page, "/publicista/anuncio/lista/");
		model.addAttribute("page_list", pageList);
		
		return "ads_list";
	}
}
