package controlador.tipoAtracciones;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.TipoAtraccion;
import servicios.TipoAtraccionServicio;

@WebServlet("/views/modificaciones/editar.do")
public class EditarTipoAtraccionesServlet extends HttpServlet {

	private static final long serialVersionUID = 7598291131560345626L;
	private TipoAtraccionServicio tipoAtraccionServicio;

	@Override
	public void init() throws ServletException {
		super.init();
		this.tipoAtraccionServicio = new TipoAtraccionServicio();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));

		TipoAtraccion tipoAtraccion = tipoAtraccionServicio.buscar(id);
		req.setAttribute("tematica", tipoAtraccion);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/modificaciones/editar.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		String tematica = req.getParameter("tematica");

		TipoAtraccion tipoAtraccion = tipoAtraccionServicio.update(id, tematica);

		if (tipoAtraccion.esValida()) {
			resp.sendRedirect("/Parque/views/modificaciones/index.do");
		} else {
			req.setAttribute("tematica", tematica);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/modificar/editar.jsp");
			dispatcher.forward(req, resp);
		}
	}
}
