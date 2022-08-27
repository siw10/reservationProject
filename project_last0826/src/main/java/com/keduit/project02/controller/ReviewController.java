package com.keduit.project02.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.keduit.project02.config.auth.PrincipalDetails;
import com.keduit.project02.domain.Review;
import com.keduit.project02.dto.PageDto;
import com.keduit.project02.service.ReviewService;

@Controller
public class ReviewController {

	@Autowired
	private ReviewService reviewService;

	@GetMapping("/myPage/review")
	public String Review() {
		return "/myPage/review";
	}

	@GetMapping("/myPage/myReview")
	public String myReview(Model model,
			@PageableDefault(size = 7, sort = "rno", direction = Sort.Direction.DESC) Pageable pageable,
			@AuthenticationPrincipal PrincipalDetails principalDetail) {
		Page<Review> reviewList = reviewService.reviewListByUsername(pageable, principalDetail.getUsername());
		PageDto<Review> reviews = new PageDto<Review>(reviewList);
		model.addAttribute("reviews", reviewList);
		model.addAttribute("startPage", reviews.getStartPage());
		model.addAttribute("endPage", reviews.getEndPage());
		return "/myPage/myReview";
	}

	@GetMapping("/swimSpa/spa")
	public String spaReview(Model model,
			@PageableDefault(size = 7, sort = "rno", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<Review> reviewList = reviewService.reviewListByBtypeList(pageable, "스파", "수영");
		PageDto<Review> reviews = new PageDto<Review>(reviewList);
		model.addAttribute("reviews", reviewList);
		model.addAttribute("startPage", reviews.getStartPage());
		model.addAttribute("endPage", reviews.getEndPage());
		return "/swimSpa/spa";
	}

	@GetMapping("/photo/id")
	public String photoReview(Model model,
			@PageableDefault(size = 7, sort = "rno", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<Review> reviewList = reviewService.reviewListByBtypeList(pageable, "포토");
		PageDto<Review> reviews = new PageDto<Review>(reviewList);
		model.addAttribute("reviews", reviewList);
		model.addAttribute("startPage", reviews.getStartPage());
		model.addAttribute("endPage", reviews.getEndPage());
		return "/photo/id";
	}

	@GetMapping("/photo/family")
	public String familyReview(Model model,
			@PageableDefault(size = 7, sort = "rno", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<Review> reviewList = reviewService.reviewListByBtypeList(pageable, "포토");
		PageDto<Review> reviews = new PageDto<Review>(reviewList);
		model.addAttribute("reviews", reviewList);
		model.addAttribute("startPage", reviews.getStartPage());
		model.addAttribute("endPage", reviews.getEndPage());
		return "/photo/family";
	}

	@GetMapping("/photo/concept")
	public String conceptReview(Model model,
			@PageableDefault(size = 7, sort = "rno", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<Review> reviewList = reviewService.reviewListByBtypeList(pageable, "포토");
		PageDto<Review> reviews = new PageDto<Review>(reviewList);
		model.addAttribute("reviews", reviewList);
		model.addAttribute("startPage", reviews.getStartPage());
		model.addAttribute("endPage", reviews.getEndPage());
		return "/photo/concept";
	}

	@GetMapping("/food/cafe")
	public String cafeReview(Model model,
			@PageableDefault(size = 7, sort = "rno", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<Review> reviewList = reviewService.reviewListByBtypeList(pageable, "식당");
		PageDto<Review> reviews = new PageDto<Review>(reviewList);
		model.addAttribute("reviews", reviewList);
		model.addAttribute("startPage", reviews.getStartPage());
		model.addAttribute("endPage", reviews.getEndPage());
		return "/food/cafe";
	}

	@GetMapping("/food/restaurant")
	public String restaurantReview(Model model,
			@PageableDefault(size = 7, sort = "rno", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<Review> reviewList = reviewService.reviewListByBtypeList(pageable,"식당");
		PageDto<Review> reviews = new PageDto<Review>(reviewList);
		model.addAttribute("reviews",reviewList);
		model.addAttribute("startPage", reviews.getStartPage());
		model.addAttribute("endPage", reviews.getEndPage());
		return "/food/restaurant";
	}

	@PostMapping("/myPage/myReview/{rno}")
	public String reviewDelete(@PathVariable Long rno) {
		reviewService.reviewDelete(rno);
		return "redirect:/myPage/myReview";
	}
}