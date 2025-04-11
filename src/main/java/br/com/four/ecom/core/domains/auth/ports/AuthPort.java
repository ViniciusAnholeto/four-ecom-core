package br.com.four.ecom.core.domains.auth.ports;

import br.com.four.ecom.core.domains.auth.models.UserDataModel;
import br.com.four.ecom.core.infrastructure.auth.models.TokenDataModel;

import java.util.Map;

/**
 * This interface defines the methods for building and parsing tokens and authorizations.
 */
public interface AuthPort {

  /**
   * Builds a token from the given model.
   *
   * @param model The model to build the token from.
   * @return The built token.
   */
  String buildToken(UserDataModel model);

  /**
   * Parses the given access token into a TokenDataModel.
   *
   * @param accessToken The access token to parse.
   * @return The parsed TokenDataModel.
   */
  TokenDataModel parseToken(String accessToken);

  /**
   * Parses the given authorization into a map.
   *
   * @param authorization The authorization to parse.
   * @return The parsed map.
   */
  Map<String, Object> parseAuthorization(String authorization);
}
