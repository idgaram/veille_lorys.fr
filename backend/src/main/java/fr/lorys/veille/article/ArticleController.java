package fr.lorys.veille.article;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

  private final ArticleRepository repository;

  public ArticleController(ArticleRepository repository) {
    this.repository = repository;
  }

  @GetMapping
  public List<Article> getAllArticles() {
    return repository.findAll();
  }
}
