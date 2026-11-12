package fr.lorys.veille.article;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ArticleRequest(
  @NotBlank @Size(max = 160) String title,
  @NotBlank String content,
  @NotBlank @Size(max = 300) String excerpt
) {}
