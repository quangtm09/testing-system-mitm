package controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.DMUtil;
import util.StringPool;
import constant.DMConstant;

/**
 * Servlet Filter implementation class DMFilter
 */
@WebFilter(description = "DM Filter", urlPatterns = { "/DMFilter" })
public class DMFilter implements Filter {

    /**
     * Default constructor.
     */
    public DMFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
		final HttpSession session = ((HttpServletRequest) request).getSession(true);

		final HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		final HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		final String currentLoggedInUser = (String) session.getAttribute("userId");

		final String cmd = DMUtil.getParameter(httpServletRequest,
				DMConstant.CMD, StringPool.BLANK);

		if ( (currentLoggedInUser == null || currentLoggedInUser == StringPool.BLANK) && !cmd.equals(DMConstant.LOGIN)) {
			this.goToPage(DMConstant.LOGIN_JSP, httpServletRequest,	httpServletResponse);
			return;
		} else {

		}

		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(final FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	public void goToPage(final String page, final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {
		final RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

}
