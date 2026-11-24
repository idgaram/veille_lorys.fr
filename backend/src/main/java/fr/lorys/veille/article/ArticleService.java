package fr.lorys.veille.article;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

  private final ArticleRepository repository;

  public ArticleService(ArticleRepository repository) {
    this.repository = repository;
  }

  List<ArticleResponse> findAll() {
    return repository.findAll().stream().map(ArticleResponse::from).toList();
  }

  ArticleResponse findById(Long id) {
    return ArticleResponse.from(getOrThrow(id));
  }

  ArticleResponse create(ArticleRequest request) {
    Article article = new Article();
    copyFields(request, article);
    return ArticleResponse.from(repository.save(article));
  }

  ArticleResponse update(Long id, ArticleRequest request) {
    Article article = getOrThrow(id);
    copyFields(request, article);
    return ArticleResponse.from(repository.save(article));
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

  private void copyFields(ArticleRequest request, Article article) {
    article.setTitle(request.title());
    article.setContent(request.content());
    article.setExcerpt(request.excerpt());
  }
}
