package floristeria.vistas;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import floristeria.modelo.Arbol;
import floristeria.modelo.Decoracion;
import floristeria.modelo.Flor;
import floristeria.modelo.Floreria;

public class VentanaListarStock extends JPanel {

	Floreria floreria;
	JScrollPane srScroll;

	public VentanaListarStock(Floreria floreria) {

		this.floreria = floreria;

		Font miFuente = new Font("Arial", Font.BOLD, 18);

		setLayout(null);

		JLabel listadoStock = new JLabel("LISTADO DE STOCK");

		listadoStock.setBounds(350, 10, 200, 50);

		listadoStock.setFont(miFuente);

		add(listadoStock);

		srScroll = new JScrollPane();

		srScroll.setBounds(200, 70, 500, 400);

		add(srScroll);

	}

	public void generarListado() {

		String[] columnas = new String[] { "Tipo", "Cantidad", "Altura", "Color", "Material", "Precio unidad" };

		int x = 0; // contador de filas de la tabla
		int aux = (int) floreria.getArboles().stream().filter(a -> (a.getCantidad() > 0)).count(); // cuanta todos los
																									// registros de
																									// arbol, flor,
																									// decoracion
		aux += (int) floreria.getFlores().stream().filter(a -> (a.getCantidad() > 0)).count();
		aux += (int) floreria.getDecoraciones().stream().filter(a -> (a.getCantidad() > 0)).count();

		if (aux > 0) { // si las listas no estan vacia

			String informacion[][] = new String[aux][6]; // creo la matriz con las sumas de los objetos de las 3 listas
															// en 'x' y las columnas en 'y'

			for (Decoracion d : floreria.getDecoraciones()) {

				if (d.getCantidad() > 0) {
					informacion[x][0] = "Decoracion";
					informacion[x][1] = "  " + d.getCantidad();
					informacion[x][4] = d.getMaterial();
					informacion[x][5] = "� " + d.getPrecio();
					x++;
				}
			}
			for (Flor f : floreria.getFlores()) {

				if (f.getCantidad() > 0) {

					informacion[x][0] = "Flores";
					informacion[x][1] = "  " + f.getCantidad();
					informacion[x][3] = f.getColor();
					informacion[x][5] = "� " + f.getPrecio();
					x++;
				}
			}
			for (Arbol p : floreria.getArboles()) {
				if (p.getCantidad() > 0) {
					informacion[x][0] = "Arbol";
					informacion[x][1] = "  " + p.getCantidad();
					informacion[x][2] = "  " + p.getAltura() + " cm";
					informacion[x][5] = "� " + p.getPrecio();
					x++;
				}
			}

			JTable table = new JTable(informacion, columnas);

			srScroll.setViewportView(table);
		} else {
			JLabel listaVacia = new JLabel(
					"EL STOCK DE LA FLORERIA:  " + floreria.getNombre() + " SE ENCUENTRA VACIO.");
			listaVacia.setHorizontalAlignment(SwingConstants.CENTER);
			srScroll.setViewportView(listaVacia);
		}
	}

}
