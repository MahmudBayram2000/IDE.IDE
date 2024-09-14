package IDE.IDE.repository;

import IDE.IDE.model.request.ReceiveOrderComponent;
import IDE.IDE.model.request.ReceiveOrderRequest;
import IDE.IDE.model.request.SendOrder;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepository {

    private static final Logger logger = LoggerFactory.getLogger(OrderRepository.class);

    @Autowired
    EntityManager entityManager;

    public List<?> sendOrder(SendOrder sendOrder) {
        logger.info("sendOrder query executed with params: {}", sendOrder);
        Query q = entityManager.createNativeQuery("CALL SendOrder('" + sendOrder.cari_id +
                "','" + sendOrder.xidmet_id +
                "','" + sendOrder.xidmet_adi +
                "','" + sendOrder.sifaris_id +
                "','" + sendOrder.sifaris_adi +
                "','" + sendOrder.lab_id +
                "','" + sendOrder.pasient +
                "','" + sendOrder.pasient_id +
                "','" + sendOrder.pasient_cins +
                "','" + sendOrder.pasient_dogum_tarixi +
                "','" + sendOrder.IDE_xidmet_id +
                "','" + sendOrder.status_id +
                "','" + sendOrder.barcode +
                "','" + sendOrder.hospital_id +
                "','" + sendOrder.gonderen_id +
                "','" + sendOrder.gonderen_adi +
                "','" + sendOrder.gonderilen_tarix + "')");
        return q.getResultList();
    }

    public List<?> recieveOrder(ReceiveOrderRequest receiveOrderRequest) {
        logger.info("recieveOrder query executed with params: {}", receiveOrderRequest);
        Query q = entityManager.createNativeQuery
                ("CALL ReceiveOrder('" + receiveOrderRequest.getBarcode() +
                        "'," + receiveOrderRequest.getHospital_id() + ")");
        return q.getResultList();
    }

    public List<?> receiveOrderStatus(ReceiveOrderComponent receiveOrderComponent) {
        logger.info("receiveOrderStatus query executed with params: {}", receiveOrderComponent);
        Query q = entityManager.createNativeQuery("CALL ReceiveOrderStatus(" + receiveOrderComponent.getReg_id() +
                "," + receiveOrderComponent.getGebul_id() +
                ",'" + receiveOrderComponent.getGebul_user() +
                "','" + receiveOrderComponent.getGebul_tarix() +
                "','" + receiveOrderComponent.getResult() + "')");
        return q.getResultList();
    }
}
