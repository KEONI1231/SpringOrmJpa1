package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    //운영장비에는 create, create-drop, update 사용금지
    //create -> drop하고 create함
    //create-drop -> create하고 마지막에 드랍함
    //update -> 변경사항만 update 됨.
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            //Member member1 = new Member(150L, "A");
            //Member member2 = new Member(160L, "B");
            //em.persist(member1);
            //em.persist(member2);//이때까지는 영속성 컨텍스트에 차곡차곡 엔티티, 쿼리도 쌓임
            //1차 캐시는 쿼리 날리지만 2차 조회는 쿼리를 날리지 않고 1차 캐시에서 값을 가져옴.


//            Member member = em.find(Member.class, 150L);
//
//            member.setName("zzzz");

            Member member = new Member(200L, "member200");
            em.persist(member);
            //em.flush(); //강제 호출 가능! //1차 캐시는 건드리지 않고, 쓰기 지연 sql 저장소에 있는 애들 + 변경 감지된 것들만.
            // 영속성 컨텍스트를 비우지 않는다!!

            System.out.println("===============");
            //commit을 하기 전까진 1차 캐시 후 쓰기지연sql 저장소에 저장되어 있다.
            tx.commit(); //커밋을 하는 순간 디비로 쿼리가 날아감.


            //플러쉬 : 영속성 컨텍스트의 변경내용을 데이터베이스에 반영하는 작업
        }catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}


//영속성 컨텍스
//엔티티를 영구 저장하는 환경
