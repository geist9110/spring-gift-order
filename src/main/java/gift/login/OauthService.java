package gift.login;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;

import java.net.URI;
import java.util.Objects;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class OauthService {

    private final RestClient restClient;

    private final KakaoOauthConfigure kakaoOauthConfigure;

    public OauthService(
        KakaoOauthConfigure kakaoOauthConfigure,
        RestClient restClient
    ) {
        this.kakaoOauthConfigure = kakaoOauthConfigure;
        this.restClient = restClient;
    }

    public URI loginKakao() {
        ResponseEntity<String> response = restClient.get()
            .uri(generateKakaoLoginURL())
            .retrieve()
            .toEntity(String.class);

        return response.getHeaders().getLocation();
    }

    private String generateKakaoLoginURL() {
        return UriComponentsBuilder.fromHttpUrl(kakaoOauthConfigure.getAuthorizeCodeURL())
            .queryParam("client_id", kakaoOauthConfigure.getClientId())
            .queryParam("redirect_uri", kakaoOauthConfigure.getRedirectURL())
            .queryParam("response_type", "code")
            .toUriString();
    }

    public String getTokenFromKakao(String code) {
        KakaoTokenResponseDTO response = restClient.post()
            .uri(kakaoOauthConfigure.getTokenURL())
            .contentType(APPLICATION_FORM_URLENCODED)
            .body(generateBodyToKakao(code))
            .retrieve()
            .toEntity(KakaoTokenResponseDTO.class)
            .getBody();

        return Objects.requireNonNull(response).getAccessToken();
    }

    private LinkedMultiValueMap<String, String> generateBodyToKakao(String code) {
        LinkedMultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("code", code);
        body.add("grant_type", "authorization_code");
        body.add("client_id", kakaoOauthConfigure.getClientId());
        body.add("redirect_uri", kakaoOauthConfigure.getRedirectURL());
        return body;
    }
}