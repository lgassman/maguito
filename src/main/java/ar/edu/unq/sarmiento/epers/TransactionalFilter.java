package ar.edu.unq.sarmiento.epers;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ar.edu.unq.sarmiento.epers.hibernate.SessionFactoryContainer;

public class TransactionalFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		SessionFactoryContainer.buildSessionFactory(false);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		try (Session session = SessionFactoryContainer.getSessionFactory().getCurrentSession()) {
			this.manageTransaction(request, response, chain, session);
		}
	}

	private void manageTransaction(ServletRequest request, ServletResponse response, FilterChain chain,
			Session session) throws ServletException, IOException {

		
		Transaction transaction = session.beginTransaction();
		try {
			chain.doFilter(request, response);
			transaction.commit();
		} catch (IOException | ServletException | RuntimeException e) {
			transaction.rollback();
			throw e;
		}

	}

	@Override
	public void destroy() {
		SessionFactoryContainer.getSessionFactory().close();
	}

}
