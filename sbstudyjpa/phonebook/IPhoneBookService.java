package com.softagape.myjpa.phonebook;

import com.softagape.myjpa.ICommonService;
import com.softagape.myjpa.category.ICategory;

import java.util.List;

public interface IPhoneBookService<T> extends ICommonService<T> {
    List<T> getListFromName(String findName);
    List<T> getListFromCategory(ICategory category);
    List<T> getListFromPhoneNumber(String findPhone);
    List<T> getListFromEmail(String findEmail);
}
