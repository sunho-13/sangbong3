package com.softagape.sbtest0628;

import com.softagape.sbtest0628.make.PhoneBook;
import com.softagape.sbtest0628.make.PhoneBookServiceImpl;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PhoneBookServiceTests {
    @Test
    public void phoneBookServiceImplTest() throws Exception {
        IPhoneBookService<IPhoneBook> service = new PhoneBookServiceImpl("-j", "");
        assertThat(service.size()).isEqualTo(0);
        IPhoneBook add1 = PhoneBook.builder().id(1L).name("add1")
                        .group(EPhoneGroup.Friends).phoneNumber("010-0000-0000")
                        .email("add1@gmail.com").build();
        IPhoneBook add2 = PhoneBook.builder().id(2L).name("add2")
                .group(EPhoneGroup.Jobs).phoneNumber("010-1111-0000")
                .email("add2@gmail.com").build();
        IPhoneBook add3 = PhoneBook.builder().id(3L).name("add3")
                .group(EPhoneGroup.Hobbies).phoneNumber("010-1111-2222")
                .email("add3@gmail.com").build();

        // insert test
        service.insert(add1);
        assertThat(service.size()).isEqualTo(1);
        service.insert(add2);
        service.insert(add3);
        // size test
        assertThat(service.size()).isEqualTo(3);
        // find test
        IPhoneBook find = service.findById(2L);
        assertThat(find.getName()).isEqualTo("add2");
        assertThat(find.getGroup()).isEqualTo(EPhoneGroup.Jobs);
        assertThat(find.getPhoneNumber()).isEqualTo("010-1111-0000");
        assertThat(find.getEmail()).isEqualTo("add2@gmail.com");
        // maxId test
        assertThat(service.getMaxId()).isEqualTo(4L);
        // remove test
        assertThat(service.remove(2L)).isEqualTo(true);
        assertThat(service.size()).isEqualTo(2);
        // getListFromName test
        assertThat(service.getListFromName("add").size()).isEqualTo(2);

        // insert fields test
        service.insert("add4", EPhoneGroup.Friends, "010-3222-3999", "add4@naver.com");
        assertThat(service.size()).isEqualTo(3);
        assertThat(service.getMaxId()).isEqualTo(5L);
        // getListFromName test
        assertThat(service.getListFromGroup(EPhoneGroup.Friends).size()).isEqualTo(2);
    }
}
