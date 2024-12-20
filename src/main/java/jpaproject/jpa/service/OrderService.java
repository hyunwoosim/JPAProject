package jpaproject.jpa.service;

import java.util.List;
import jpaproject.jpa.domain.Delivery;
import jpaproject.jpa.domain.Member;
import jpaproject.jpa.domain.Order;
import jpaproject.jpa.domain.OrderItem;
import jpaproject.jpa.domain.item.Item;
import jpaproject.jpa.repository.ItemRepository;
import jpaproject.jpa.repository.MemberRepository;
import jpaproject.jpa.repository.OrderRepository;
import jpaproject.jpa.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;


    /**
     * 주문
     */
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {

        // 엔티티 조회
        Member member = memberRepository.findById(memberId).get();
        Item item = itemRepository.findOne(itemId);

        // 배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        // 주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        // 주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        // 주문 저장
        orderRepository.save(order);

        return order.getId();

    }

    /**
     * 전체 취소
     */
    @Transactional
    public void cancelOrder(Long orderId) {
        // 주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);
        // 주문 취소
        order.cancel();

    }

    /**
     * 개별 취소
     */
    @Transactional
    public void cancelOrderItem(Long orderItemId) {
        OrderItem oneOrderItem = orderRepository.findOneOrderItem(orderItemId);
        System.out.println("============================");
        System.out.println("@@@Orderservice@@@@");
        System.out.println("oneOrderItem.getId() = " + oneOrderItem.getId());
        System.out.println("============================");
        oneOrderItem.cancel();
    }

    // 검색
    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.QueryDslFindAll(orderSearch);
    }


    public List<Order> findAllOrders() {
        return orderRepository.findAll();

    }
}