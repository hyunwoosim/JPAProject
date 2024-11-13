//package jpaproject.jpa.service;
//
//
//import static org.assertj.core.api.Fail.fail;
//import static org.springframework.test.util.AssertionErrors.assertEquals;
//
//import jakarta.persistence.EntityManager;
//import jpaproject.jpa.domain.Address;
//import jpaproject.jpa.domain.Member;
//import jpaproject.jpa.domain.Order;
//import jpaproject.jpa.domain.OrderStatus;
//import jpaproject.jpa.domain.item.Book;
//import jpaproject.jpa.domain.item.Item;
//import jpaproject.jpa.repository.OrderRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.transaction.annotation.Transactional;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//@Transactional
//public class OrderServiceTest {
//
//    @Autowired
//    EntityManager em;
//
//    @Autowired
//    OrderService orderService;
//
//    @Autowired
//    OrderRepository orderRepository;
//
//    @Test
//    public void 상품주문() throws Exception {
//        //given
//        Member member = createMember();
//
//        Book book = createBook("시골JPA", 10000, 10);
//
//        int orderCount = 2;
//
//        //when
//        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
//
//        //then
//        Order getOrder = orderRepository.findOne(orderId);
//
//        assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER, getOrder.getStatus());
//        assertEquals("주문한 상품 종류 수가 정확해야한다.", 1, getOrder.getOrderItems().size());
//        assertEquals("주문 가격은 가격 * 수량이다.", 10000 * orderCount, getOrder.getTotalPrice());
//        assertEquals("주문 수량만큼 재고가 줄어야한다.", 8, book.getStockQuantity());
//
//    }
//
//    //    @Test(expected = NotEnoughStockException.class)
//    public void 상품주문_재고수량초과() throws Exception {
//        //given
//        Member member = createMember();
//        Item item = createBook("시골JPA", 10000, 8);
//
//        int orderCount = 10;
//
//        //when
//        orderService.order(member.getId(), item.getId(), orderCount);
//
//        //then
//        fail("재고 수량 부족 예외가 발생해야 한다.");
//    }
//
//
//    @Test
//    public void 주문취소() throws Exception {
//        //given
//        Member member = createMember();
//        Book item = createBook("책1", 10000, 10);
//
//        int orderCount = 2;
//
//        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);
//        //when
//        orderService.cancelOrder(orderId);
//
//        //then
//        Order getOrder = orderRepository.findOne(orderId);
//
//        assertEquals("주문 취소시 상태는 CANCEL이다.", OrderStatus.CANCEL, getOrder.getStatus());
//        assertEquals("주문이 취소된 상품은 그만큼 재고가 증가해야한다.", 10, item.getStockQuantity());
//    }
//
//
//    private Book createBook(String name, int price, int stockQuantity) {
//        Book book = new Book();
//        book.setName(name);
//        book.setPrice(price);
//        book.setStockQuantity(stockQuantity);
//        em.persist(book);
//        return book;
//    }
//
//    private Member createMember() {
//        Member member = new Member();
//        member.setName("회원1");
//        member.setAddress(new Address("서울", "강가", "123-123"));
//        em.persist(member);
//        return member;
//    }
//}