package com.APP.demo.mongo.Services;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.APP.demo.mongo.Documentos.Carrito;
import com.APP.demo.mongo.Documentos.CarritoVersion;
import com.APP.demo.mongo.Repository.CarritoRepository;
import com.APP.demo.mongo.Repository.CarritoVersionRepository;

@Service
public class CarritoServices {

	@Autowired
	private CarritoRepository carritoRepository;
	
	@Autowired 
	private CarritoVersionRepository carritoAntRepository;
	
	public Carrito agregarProducto(String userId, String nProduct, int cant) {
        Carrito carrito = carritoRepository.findByUserId(userId).orElse(new Carrito());
        if (carrito.getProductos() == null) {
            carrito.setProductos(new HashMap<>());
            carrito.setId(UUID.randomUUID().toString());
            carrito.setUserId(userId);
        }
        carrito.getProductos().put(nProduct, carrito.getProductos().getOrDefault(nProduct, 0) + cant);
        return carritoRepository.save(carrito);
    }
	
	public Carrito obtenerCarrito(String userId) {
		Carrito carrito = carritoRepository.findByUserId(userId).orElse(new Carrito());
        if (carrito.getProductos() == null) {
            carrito.setProductos(new HashMap<>());
            carrito.setId(UUID.randomUUID().toString());
            carrito.setUserId(userId);
        }
        return carrito;
	}

    public Carrito quitarProducto(String userId, String nProduct) {
        Carrito carrito = carritoRepository.findByUserId(userId).orElse(new Carrito());
        carrito.getProductos().remove(nProduct);
        return carritoRepository.save(carrito);
    }

    public Carrito ActualizarProdCant(String userId, String nProduct, int cant) {
        Carrito carrito = carritoRepository.findByUserId(userId).orElseThrow();
        carrito.getProductos().put(nProduct, cant);
        return carritoRepository.save(carrito);
    }
    
    public List<CarritoVersion> CarritosAntiguos (String userId){
    	return carritoAntRepository.findByUserIdOrderByFechaDesc(userId);
    }
    
    public void CarritoCambio(String id2, String userId) {
    	CarritoVersion carritoAnt = carritoAntRepository.findById2(id2);
    	CarritoVersion CarritoAux = new CarritoVersion();
    	 Carrito carrito = carritoRepository.findByUserId(userId).orElse(new Carrito());
    	 if (carrito.getProductos() == null) {
             carrito.setProductos(new HashMap<>());
             carrito.setId(UUID.randomUUID().toString());
             carrito.setUserId(userId);
         }
    	 CarritoAux.setProductos(carritoAnt.getProductos());
    	 carritoAnt.setProductos(carrito.getProductos());
    	 carrito.setProductos(CarritoAux.getProductos());
    	 carritoAnt.setFecha(LocalDateTime.now());
    	 carritoAntRepository.save(carritoAnt);
    	 carritoRepository.save(carrito);
    }
}
