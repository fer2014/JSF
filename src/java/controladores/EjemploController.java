/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import daos.CustomerDAO;
import daos.PurchaseOrderDAO;
import entidades.Customer;
import entidades.PurchaseOrder;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author 17
 */
@ManagedBean
@SessionScoped
public class EjemploController {

    @EJB
    private CustomerDAO customerDAO;

    @EJB
    private PurchaseOrderDAO purchaseOrderDAO;

    private String cadenaBusqueda;
    private List<Customer> listaClientes;
    private Customer clienteActual;
    private List<PurchaseOrder> listaPedidosClienteActual;
    private PurchaseOrder pedidoActual;

    @PostConstruct
    public void inicializar() {
        listaClientes = customerDAO.buscarTodos();
        clienteActual = listaClientes.get(0);
        listaPedidosClienteActual = purchaseOrderDAO.buscarPorCliente(clienteActual.getCustomerId());
        pedidoActual = null;
    }

    public String doVerPedidos(Customer cliente) {
        clienteActual = cliente;
        listaPedidosClienteActual = purchaseOrderDAO.buscarPorCliente(cliente.getCustomerId());
        return "detalleCliente";
    }

    public String doBuscarCliente() {
        listaClientes = customerDAO.buscarPorNombre(cadenaBusqueda);
        clienteActual = listaClientes.get(0);
        return "index";
    }

    public String doNuevoCliente() {
        clienteActual = new Customer();
        listaPedidosClienteActual = null;
        return "nuevoCliente";
    }

    public String doGuardarCliente() {
        customerDAO.crear(clienteActual);
        return "index";
    }

    /**
     * Creates a new instance of EjemploController
     */
    public EjemploController() {
    }

    /**
     * @return the customerDAO
     */
    public CustomerDAO getCustomerDAO() {
        return customerDAO;
    }

    /**
     * @param customerDAO the customerDAO to set
     */
    public void setCustomerDAO(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    /**
     * @return the purchaseOrderDAO
     */
    public PurchaseOrderDAO getPurchaseOrderDAO() {
        return purchaseOrderDAO;
    }

    /**
     * @param purchaseOrderDAO the purchaseOrderDAO to set
     */
    public void setPurchaseOrderDAO(PurchaseOrderDAO purchaseOrderDAO) {
        this.purchaseOrderDAO = purchaseOrderDAO;
    }

    /**
     * @return the cadenaBusqueda
     */
    public String getCadenaBusqueda() {
        return cadenaBusqueda;
    }

    /**
     * @param cadenaBusqueda the cadenaBusqueda to set
     */
    public void setCadenaBusqueda(String cadenaBusqueda) {
        this.cadenaBusqueda = cadenaBusqueda;
    }

    /**
     * @return the listaClientes
     */
    public List<Customer> getListaClientes() {
        return listaClientes;
    }

    /**
     * @param listaClientes the listaClientes to set
     */
    public void setListaClientes(List<Customer> listaClientes) {
        this.listaClientes = listaClientes;
    }

    /**
     * @return the clienteActual
     */
    public Customer getClienteActual() {
        return clienteActual;
    }

    /**
     * @param clienteActual the clienteActual to set
     */
    public void setClienteActual(Customer clienteActual) {
        this.clienteActual = clienteActual;
    }

    /**
     * @return the listaPedidosClienteActual
     */
    public List<PurchaseOrder> getListaPedidosClienteActual() {
        return listaPedidosClienteActual;
    }

    /**
     * @param listaPedidosClienteActual the listaPedidosClienteActual to set
     */
    public void setListaPedidosClienteActual(List<PurchaseOrder> listaPedidosClienteActual) {
        this.listaPedidosClienteActual = listaPedidosClienteActual;
    }

    /**
     * @return the pedidoActual
     */
    public PurchaseOrder getPedidoActual() {
        return pedidoActual;
    }

    /**
     * @param pedidoActual the pedidoActual to set
     */
    public void setPedidoActual(PurchaseOrder pedidoActual) {
        this.pedidoActual = pedidoActual;
    }

}
