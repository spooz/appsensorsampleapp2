package com.bartoszbalukiewicz.controller;

        import com.bartoszbalukiewicz.form.MessageForm;
        import com.bartoszbalukiewicz.form.TopicForm;
        import com.bartoszbalukiewicz.form.validator.MessageFormValidator;
        import com.bartoszbalukiewicz.service.ForumService;
        import org.json.JSONObject;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.MediaType;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.validation.BindingResult;
        import org.springframework.web.bind.WebDataBinder;
        import org.springframework.web.bind.annotation.*;

        import javax.validation.Valid;

/**
 * Created by postgres on 2016-09-20.
         */
@Controller
public class ForumController {

    @Autowired
    private ForumService forumService;

    @Autowired
    private MessageFormValidator messageFormValidator;

    @InitBinder("messageForm")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(messageFormValidator);
    }

    @PostMapping("/topic/add")
    @ResponseStatus(HttpStatus.OK)
    public void addTopic(@RequestBody @Valid TopicForm form,  BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return;

        forumService.createTopic(form);
    }

    @GetMapping(value="/topic/all", produces= "application/json")
    @ResponseBody
    public String getTopics() {
        JSONObject data = new JSONObject();
        data.put("data", forumService.getAll());
        return data.toString();
    }

    @GetMapping("/topic/{topicId}/messages")
    public String messages (@PathVariable("topicId") Long topicId, Model model){
        model.addAttribute("topicId", topicId);
        model.addAttribute("messages", forumService.getMessagesForTopic(topicId));
        model.addAttribute("messageForm", new MessageForm());
        return "messages";
    }

    @PostMapping("/topic/{topicId}/messages")
    public String addMessage(@PathVariable(value="topicId") Long topicId, @ModelAttribute @Valid MessageForm messageForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return "redirect:/topic/" + topicId + "/messages";

        forumService.createMessage(messageForm, topicId);
        return "redirect:/topic/" + topicId + "/messages";
    }
}
