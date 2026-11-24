package fr.lorys.veille.article;

public record ArticleResponse(
  Long id,
  String title,
  String content,
  String excerpt
) {
  static ArticleResponse from(Article article) {
    return new ArticleResponse(
      article.getId(),
      article.getTitle(),
      article.getContent(),
      article.getExcerpt()
    );
  }
}
