package com.bartoszbalukiewicz.controller;

        import com.bartoszbalukiewicz.form.TopicForm;
        import com.bartoszbalukiewicz.service.ForumService;
        import org.json.JSONObject;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.MediaType;
        import org.springframework.stereotype.Controller;
        import org.springframework.validation.BindingResult;
        import org.springframework.web.bind.annotation.*;

        import javax.validation.Valid;

/**
 * Created by postgres on 2016-09-20.
         */
@Controller
public class ForumController {

    @Autowired
    private ForumService forumService;

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
}
