package com.ddm.climatempo.api.config.token;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import com.ddm.climatempo.api.security.UsuarioClienteSistema;

public class CustomTokenEnhancer implements TokenEnhancer{

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Map<String, Object> addInfo = new HashMap<>();
				
		if (authentication.getPrincipal().getClass().equals(UsuarioClienteSistema.class)) {
			
			UsuarioClienteSistema usuarioClienteSistema = (UsuarioClienteSistema) authentication.getPrincipal();
			addInfo.put("nome", usuarioClienteSistema.getCliente().getNm_Cliente());
			addInfo.put("codigo", usuarioClienteSistema.getCliente().getCd_Cliente());
			
		}
		
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(addInfo);
		return accessToken;
	}
	

}
