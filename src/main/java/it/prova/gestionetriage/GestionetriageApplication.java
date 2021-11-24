package it.prova.gestionetriage;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import it.prova.gestionetriage.model.Authority;
import it.prova.gestionetriage.model.AuthorityName;
import it.prova.gestionetriage.model.Dottore;
import it.prova.gestionetriage.model.Paziente;
import it.prova.gestionetriage.model.StatoPaziente;
import it.prova.gestionetriage.model.StatoUtente;
import it.prova.gestionetriage.model.User;
import it.prova.gestionetriage.security.repository.AuthorityRepository;
import it.prova.gestionetriage.security.repository.UserRepository;
import it.prova.gestionetriage.service.DottoreService;
import it.prova.gestionetriage.service.PazienteService;

@SpringBootApplication
public class GestionetriageApplication {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	AuthorityRepository authorityRepository;
	@Autowired
	PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(GestionetriageApplication.class, args);
	}

	@Bean
	public CommandLineRunner initDottori(DottoreService dottoreService, PazienteService pazienteService) {
		return (args) -> {

			// inizializzo il Db
			dottoreService.save(new Dottore("nome1", "cognome1", "codice1"));
			dottoreService.save(new Dottore("nome2", "cognome2", "codice2"));
			dottoreService.save(new Dottore("nome3", "cognome3", "codice3"));
			dottoreService.save(new Dottore("nome4", "cognome4", "codice4"));
			dottoreService.save(new Dottore("nome5", "cognome5", "codice5"));
			dottoreService.save(new Dottore("nome6", "cognome6", "codice6"));
			dottoreService.save(new Dottore("nome7", "cognome7", "codice7"));
			dottoreService.save(new Dottore("nome8", "cognome8", "codice8"));

			pazienteService.save(
					new Paziente("paziente1", "cognome6", new Date(), "paziente1", StatoPaziente.IN_ATTESA_VISITA));
			pazienteService.save(
					new Paziente("paziente2", "cognome7", new Date(), "paziente2", StatoPaziente.IN_ATTESA_VISITA));
			pazienteService.save(
					new Paziente("paziente3", "cognome8", new Date(), "paziente3", StatoPaziente.IN_ATTESA_VISITA));

			// verifico inserimento
			System.out.println("Elenco Dottori");
			dottoreService.listAll().forEach(dottItem -> {
				System.out.println(dottItem);
			});

			// Ora la parte di sicurezza
			User user = userRepository.findByUsername("admin").orElse(null);

			if (user == null) {

				/**
				 * Inizializzo i dati del mio test
				 */

				Authority authorityAdmin = new Authority();
				authorityAdmin.setName(AuthorityName.ROLE_ADMIN);
				authorityAdmin = authorityRepository.save(authorityAdmin);

				Authority authorityUser = new Authority();
				authorityUser.setName(AuthorityName.ROLE_SUB_OPERATOR);
				authorityUser = authorityRepository.save(authorityUser);

				List<Authority> authorities = Arrays.asList(new Authority[] { authorityAdmin, authorityUser });

				user = new User();
				user.setAuthorities(authorities);
				user.setEnabled(true);
				user.setUsername("admin");
				user.setPassword(passwordEncoder.encode("admin"));
				user.setEmail("admin@example.com");
				user.setStato(StatoUtente.ATTIVO);
				user = userRepository.save(user);

			}

			User commonUser = userRepository.findByUsername("commonUser").orElse(null);

			if (commonUser == null) {

				/**
				 * Inizializzo i dati del mio test
				 */

				Authority authorityUser = authorityRepository.findByName(AuthorityName.ROLE_SUB_OPERATOR);
				if (authorityUser == null) {
					authorityUser = new Authority();
					authorityUser.setName(AuthorityName.ROLE_SUB_OPERATOR);
					authorityUser = authorityRepository.save(authorityUser);
				}

				List<Authority> authorities = Arrays.asList(new Authority[] { authorityUser });

				commonUser = new User();
				commonUser.setAuthorities(authorities);
				commonUser.setEnabled(true);
				commonUser.setUsername("commonUser");
				commonUser.setPassword(passwordEncoder.encode("commonUser"));
				commonUser.setEmail("commonUser@example.com");
				commonUser.setStato(StatoUtente.ATTIVO); ;
				commonUser = userRepository.save(commonUser);

			}
		};
	}

}
