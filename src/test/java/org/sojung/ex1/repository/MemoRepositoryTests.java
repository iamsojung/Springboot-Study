package org.sojung.ex1.repository;

import org.junit.jupiter.api.Test;
import org.sojung.ex1.entity.Memo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class MemoRepositoryTests {

    @Autowired
    MemoRepository memoRepository;

    @Test
    public void testClass() {
        System.out.println(memoRepository.getClass().getName());
    }

    @Test
    public void testlnsertDummies(){
        IntStream.rangeClosed(1,100).forEach(i -> {
            Memo memo = Memo.builder().memoText( "Sample ... "+i).build () ;
            memoRepository.save(memo);
        });
    }

    @Transactional
    @Test
    public void testSelect () {
//
        Long mno = 100L;
        Optional<Memo> result = memoRepository.findById(mno);
        System.out.println(" ==================================");
        if(result.isPresent()){
            Memo memo = result.get();
            System.out.println(memo); }
        }

    @Test
    public void testUpdate(){

        Memo memo = Memo.builder().mno(100L).memoText("Update Text").build();

        System.out.println(memoRepository.save(memo));

   }

   @Test
    public void testDelete(){

        Long mno = 100L;
        memoRepository.deleteById(mno);
   }

    @Test
    public void testPageDefault(){

        //1페이지 10개
       Pageable pageable = PageRequest.of(0,10);
       Page<Memo> result = memoRepository.findAll(pageable);
       System.out.println(result);

   }


}
