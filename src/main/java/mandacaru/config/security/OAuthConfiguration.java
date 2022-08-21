package mandacaru.config.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@SuppressWarnings("deprecation")
@Configuration
public class OAuthConfiguration {

	@Value("${security.oauth2.client.scope}")
	private String scope;
	@Value("${security.oauth2.client.client-id}")
	private String client;
	@Value("${security.oauth2.client.client-secret}")
	private String secret;
	@Value("${security.oauth2.client.grant-type}")
	private String grantType;

	private static final String RESOURCE_ID = "mandacaru";

	@Configuration
	@EnableResourceServer
	protected class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

		@Override
		public void configure(ResourceServerSecurityConfigurer resources) {
			resources.resourceId(RESOURCE_ID);
		}
	}

	@Configuration
	@EnableAuthorizationServer
	protected class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

		@Autowired
		@Qualifier("authenticationManagerBean")
		private AuthenticationManager authenticationManager;

		@Autowired
		private UserDetailsServiceImpl userDetailsService;

		@Autowired
		private PasswordEncoder passwordEncoder;

		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
			TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
	        tokenEnhancerChain.setTokenEnhancers(List.of(new CustomTokenEnhancer(), accessTokenConverter()));
			endpoints.
				accessTokenConverter(accessTokenConverter()).
				tokenEnhancer(tokenEnhancerChain).
				authenticationManager(this.authenticationManager)
				.userDetailsService(userDetailsService);
		}

		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
			clients.inMemory().withClient(client).authorizedGrantTypes(grantType).scopes(scope).resourceIds(RESOURCE_ID)
					.secret(passwordEncoder.encode(secret)).accessTokenValiditySeconds(50000);
		}
		
		@Bean
		public JwtAccessTokenConverter accessTokenConverter() {
			JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
			converter.setSigningKey(secret);
			return converter;
		}

	}
}