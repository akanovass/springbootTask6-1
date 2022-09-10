package com.example.springbootTask61.controller;

import com.example.springbootTask61.ApplicationRequest;
import com.example.springbootTask61.Courses;
import com.example.springbootTask61.CoursesRepository;
import com.example.springbootTask61.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private CoursesRepository coursesRepository;

    @GetMapping("/")
    public String index(Model model) {
        List<ApplicationRequest> appRequests = requestRepository.findAll();
        model.addAttribute("appRequests", appRequests);
        return "index";
    }

    @GetMapping("/add")
    public String add(Model model) {
        List<Courses> courses = coursesRepository.findAll();
        model.addAttribute("courses", courses);
        return "add";
    }

    @PostMapping("/add")
    public String add(@RequestParam(name = "name") String name,
                      @RequestParam(name = "course_id") Long course_id,
                      @RequestParam(name = "number") String number,
                      @RequestParam(name = "commentary") String commentary) {
        ApplicationRequest appRequest = new ApplicationRequest();
        appRequest.setUserName(name);
        appRequest.setPhone(number);
        appRequest.setCommentary(commentary);
        appRequest.setHandled(false);

        Courses course = coursesRepository.findById(course_id).orElse(null);
        appRequest.setCourse(course);
        requestRepository.save(appRequest);

        return "redirect:/";
    }

    @GetMapping("/newrequests")
    public String newrequests(Model model) {
        List<ApplicationRequest> appRequests = requestRepository.findAll();
        model.addAttribute("appRequests", appRequests);
        return "newrequests";
    }

    @GetMapping("/handledrequests")
    public String handledrequests(Model model) {
        List<ApplicationRequest> appRequests = requestRepository.findAll();
        model.addAttribute("appRequests", appRequests);
        return "handledrequests";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable(name = "id") Long id,
                          Model model) {
        ApplicationRequest appRequest = requestRepository.findById(id).orElse(null);
        List<Courses> courses = coursesRepository.findAll();
        if (appRequest != null) {
            model.addAttribute("appRequest", appRequest);
            model.addAttribute("courses", courses);
        }
        return "details";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") Long id,
                         Model model) {
        requestRepository.deleteById(id);

        return "redirect:/";
    }

    @GetMapping("/tohandled/{id}")
    public String handled(@PathVariable(name = "id") Long id) {
        ApplicationRequest appRequest = requestRepository.findById(id).orElse(null);
        if (appRequest != null) {
            appRequest.setHandled(true);
            requestRepository.save(appRequest);
        }
            return "redirect:/details/" + id;
    }
}
