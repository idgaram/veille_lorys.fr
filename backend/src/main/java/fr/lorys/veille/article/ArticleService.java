package fr.lorys.veille.article;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

  private final ArticleRepository repository;

  public ArticleService(ArticleRepository repository) {
    this.repository = repository;
  }

  List<Article> findAll() {
    return repository.findAll();
  }

  Article findById(Long id) {
    return getOrThrow(id);
  }

  Article create(Article article) {
    return repository.save(article);
  }

  Article update(Long id, Article changes) {
    Article article = getOrThrow(id);
    article.setContent(changes.getContent());
    article.setExcerpt(changes.getExcerpt());
    article.setTitle(changes.getTitle());
    return repository.save(article);
  }

  void delete(Long id) {
    Article article = getOrThrow(id);
    repository.delete(article);
  }

  private Article getOrThrow(Long id) {
    return repository
      .findById(id)
      .orElseThrow(() -> new ArticleNotFoundException(id));
  }
}
