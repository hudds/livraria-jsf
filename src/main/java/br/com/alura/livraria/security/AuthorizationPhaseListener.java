package br.com.alura.livraria.security;

import static br.com.alura.livraria.session.SecuritySessionAttributesNames.LAST_PATH_UNAUTHORIZED;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;

import br.com.alura.livraria.bean.Redirection;
import br.com.alura.livraria.session.SessionAttributes;
import br.com.alura.livraria.session.UserSession;

public class AuthorizationPhaseListener implements PhaseListener {

	@Inject
	private UserSession userSession;
	@Inject
	private SessionAttributes sessionAttributes;
	@Inject
	private AccessRules accessRules;

	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent event) {
		FacesContext context = event.getFacesContext();
		String viewId = context.getViewRoot().getViewId();
		if(accessRules.mustBeAuthenticatedToPath(viewId) && !userSession.isAuthenticated()) {
			sessionAttributes.addFlashAttribute(LAST_PATH_UNAUTHORIZED, viewId);
			redirectToLoginPage(context);
		}
		
	}

	private void redirectToLoginPage(FacesContext context) {
		NavigationHandler navigationHandler = context.getApplication().getNavigationHandler();
		Redirection redirection = new Redirection(SecurityPaths.PATH_LOGIN);
		navigationHandler.handleNavigation(context, null, redirection.toString());
		context.renderResponse();
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
