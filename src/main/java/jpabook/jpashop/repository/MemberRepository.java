package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    public Member findById(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll(){
        /**
         * 엔티티 객체를 상대로 쿼리문
         * */
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findBy(String name, Address address){
        String query = "select m from Member m where 1=1 ";
        TypedQuery<Member> typedQuery = null;

        if(name != null){
            query += "and m.name = :name";
        }

        if(address != null){
            query += "and (" +
                    "m.address.city = :address.city or " +
                    "m.address.street = :address.street or " +
                    "m.address.zipcode = :address.zipcode)";
        }

        typedQuery = em.createQuery(query, Member.class);

        if(name != null){
            typedQuery.setParameter("name", name);
        }
        if(address != null){
            typedQuery.setParameter("address", address);
        }

        return typedQuery.getResultList();
    }
}
