package by.ansgar.navigation.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import by.ansgar.navigation.dao.impl.CitationDAOImpl;
import by.ansgar.navigation.entity.Citation;
import by.ansgar.navigation.service.CitationService;

@Controller
public class CitationController {

	private static final Logger LOG = Logger
			.getLogger(CitationController.class);
	@Autowired
	private CitationService citationServ;

	@RequestMapping(value = "/citations_{page}_{cit}")
	public ModelAndView openCitationPage(@PathVariable int page,
			@PathVariable String cit) {
		ModelAndView mav = new ModelAndView();
		List<Citation> citations = new ArrayList<Citation>();
		List<Citation> citationsOnPage = new ArrayList<Citation>();
		List<Integer> pages = new ArrayList<Integer>();
		try {
			citations = citationServ.getAllCitation();

			if (page == 0) {
				page = 1;
			}
			switch (cit) {
			case "all":
				citationsOnPage = citationServ.getAllCitation(page);
				break;
			case "liked":
				citationsOnPage = citationServ.getLikedCitation(page);
				break;
			}

			for (int j = 0; j < Math.ceil((double) citations.size()
					/ (double) CitationDAOImpl.MAX_RES); j++) {
				pages.add(j + 1);
				System.out.println(pages);
			}
		} catch (SQLException e) {
			LOG.error("Cannot show all citation!", e);
		}
		mav.addObject("all_citations", citationsOnPage);
		mav.addObject("cit", cit);
		mav.addObject("page", pages);
		mav.setViewName("citations");
		return mav;
	}

	@RequestMapping(value = "/update_citation-{id}", method = {
			RequestMethod.POST })
	public ModelAndView editCitation(@PathVariable long id,
			@ModelAttribute Citation citation) {
		ModelAndView mav = new ModelAndView();
		citation.setId(id);
		try {
			citationServ.editCitation(citation);
		} catch (SQLException e) {
			LOG.error("Cannot update citation!", e);
		}
		mav.setViewName("citaitons");
		return mav;
	}

	@RequestMapping(value = "/delete_citation-{id}")
	public ModelAndView deleteCitation(@PathVariable long id,
			@ModelAttribute Citation citation) {
		ModelAndView mav = new ModelAndView();
		citation.setId(id);
		try {
			citationServ.deleteCitation(citation);
		} catch (SQLException e) {
			LOG.error("Cannot delete citation!", e);
		}
		mav.setViewName("citaitons");
		return mav;
	}

	@RequestMapping(value = "/doLiked")
	public @ResponseBody String doLiked(@RequestParam long id) {
		try {
			Citation citation = citationServ.getCitationById(id);
			citation.setCitation(citation.getCitation());
			citation.setAuthor(citation.getAuthor());
			citation.setAuthor_id(citation.getAuthor_id());
			citation.setBook(citation.getBook());
			citation.setBook_id(citation.getBook_id());
			citation.setDate(citation.getDate());
			citation.setLiked(1);
			citationServ.editCitation(citation);
			System.out.println(id);
		} catch (SQLException e) {
			LOG.error("Can not liked", e);
		}
		return "Liked";
	}
}
