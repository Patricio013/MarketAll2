package com.APP.demo.mongo.Services;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.APP.demo.mongo.Documentos.Factura;
import com.APP.demo.mongo.Documentos.Pedido;
import com.APP.demo.mongo.Documentos.PagoLog;
import com.APP.demo.mongo.Repository.*;

@Service
public class FacturaService {
	
	@Autowired
    private PedidosRepository pedidoRepository;

    @Autowired
    private FacturaRepository facturaRepository;
    
    @Autowired 
	private PagoLogRepository pagologRepository;
    
    public Factura facturarPedido(String pedidoId, String formaDePago) {
        Pedido pedido = pedidoRepository.findById(pedidoId).orElseThrow();
        Factura factura = new Factura();
        PagoLog pagolog = new PagoLog();
        factura.setId(UUID.randomUUID().toString());
        factura.setPedidoId(pedidoId);
        factura.setTotal(CalcularMonto(pedido.getTotal(), formaDePago));
        factura.setFormaDePago(formaDePago);
        pagolog.setId(UUID.randomUUID().toString());
        pagolog.setFacturaId(factura.getId());
        pagolog.setFecha(LocalDateTime.now());
        pagolog.setOperador(pedido.getUserId());
        pagolog.setMedio(formaDePago);
        pagolog.setMonto(factura.getTotal());
        pagologRepository.save(pagolog);
        pedidoRepository.delete(pedido);
        return facturaRepository.save(factura);
    }
    
    private double CalcularMonto(double monto, String formaPago) {
    	if (formaPago == "Efectivo") {
    		monto = Efectivo(monto);
    	} else {
    		if (formaPago == "Credito") {
    			monto = Credito(monto);
    		} else {
    			monto = Debito(monto);
    		}
    	}
    	return monto;
    }
    
    private double Efectivo(double valor) {
		 double descuento= 10;
		 double total = valor - (valor*descuento/100);
		 return total;
	 }
	 
	 private double Credito(double valor) {
		 //Solo con 3 cuotas
		 double Recargo;
		 double Total;
		 Recargo = valor + valor*(12/100);
		 Total = Recargo/3;
		 return Total;
	 }
	 
	 private double Debito(double valor) {
		 return valor;
	 }
}
