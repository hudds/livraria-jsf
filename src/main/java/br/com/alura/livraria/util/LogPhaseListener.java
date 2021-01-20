package br.com.alura.livraria.util;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class LogPhaseListener implements PhaseListener {

	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent event) {
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		Logger logger = Logger.getGlobal();
		logger.info("FASE:" + event.getPhaseId());
		logger.log(Level.INFO, "FASE:" + event.getPhaseId());
		System.out.println("FASE:" + event.getPhaseId());
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

}
