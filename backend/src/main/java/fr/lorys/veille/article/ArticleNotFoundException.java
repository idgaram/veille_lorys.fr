package fr.lorys.veille.article;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ArticleNotFoundException extends RuntimeException {

  ArticleNotFoundException(Long id) {
    super("Could not find article " + id);
  }
}
