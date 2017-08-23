package pl.coderslab.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.entities.StudentGroupRepo;
import pl.coderslab.repository.StudentExamRepository;
import pl.coderslab.repository.StudentGroupExamRepository;
import pl.coderslab.repository.StudentGroupRepoRepository;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/student-group-repos")
public class StudentGroupReposController {


    @Autowired
    StudentGroupRepoRepository studentGroupRepoRepository;

    @RequestMapping("/{studentGroupId}/list")
    public String list(Model model, @PathVariable("studentGroupId") long studentGroupId){
        model.addAttribute("list", studentGroupRepoRepository.findAllByStudentGroupId(studentGroupId));
        return "studentGroupRepo/list";
    }

    /**
     * @todo add share on Github - by github Manager
     * @param model
     * @param studentGroupId
     * @param request
     * @return
     */
    @RequestMapping("/{studentGroupRepoId}/share")
    public String share(Model model, @PathVariable("studentGroupRepoId") long studentGroupId, HttpServletRequest request){

        StudentGroupRepo studentGroupRepo = studentGroupRepoRepository.findOne(studentGroupId);
        studentGroupRepo.setSharedDate(LocalDateTime.now());
        studentGroupRepo.setShared(true);
        studentGroupRepoRepository.save(studentGroupRepo);
        return "redirect:"+ request.getHeader("Referer");
    }

}
