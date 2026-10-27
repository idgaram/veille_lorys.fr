package fr.lorys.veille.article;

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

  private final ArticleRepository repository;

  public ArticleController(ArticleRepository repository) {
    this.repository = repository;
  }

  @GetMapping
  public List<Article> getAllArticles() {
    return repository.findAll();
  }

  @GetMapping("/{id}")
  public Article getArticle(@PathVariable Long id) {
    return repository
      .findById(id)
      .orElseThrow(() -> new ArticleNotFoundException(id));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Article createArticle(@RequestBody Article article) {
    return repository.save(article);
  }

  @PutMapping("/{id}")
  public Article updateArticle(
    @PathVariable Long id,
    @RequestBody Article newArticle
  ) {
    return repository
      .findById(id)
      .map(article -> {
        article.setTitle(newArticle.getTitle());
        article.setExcerpt(newArticle.getExcerpt());
        article.setContent(newArticle.getContent());
        return repository.save(article);
      })
      .orElseThrow(() -> new ArticleNotFoundException(id));
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteArticle(@PathVariable Long id) {
    Article article = repository
      .findById(id)
      .orElseThrow(() -> new ArticleNotFoundException(id));
    repository.delete(article);
  }
}
