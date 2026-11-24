package fr.lorys.veille.article;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

  private final ArticleService service;

  public ArticleController(ArticleService service) {
    this.service = service;
  }

  @GetMapping
  public List<ArticleResponse> getAllArticles() {
    return service.findAll();
  }

  @GetMapping("/{id}")
  public ArticleResponse getArticle(@PathVariable Long id) {
    return service.findById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ArticleResponse createArticle(
    @RequestBody @Valid ArticleRequest articleRequest
  ) {
    return service.create(articleRequest);
  }

  @PutMapping("/{id}")
  public ArticleResponse updateArticle(
    @PathVariable Long id,
    @RequestBody @Valid ArticleRequest articleRequest
  ) {
    return service.update(id, articleRequest);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteArticle(@PathVariable Long id) {
    service.delete(id);
  }
}
