package org.apache.shiro.exception;


import com.ppcxy.common.exception.ServiceException;

/**
 * <p>Date: 13-3-11 下午8:19
 * <p>Version: 1.0
 */
public class UserException extends ServiceException {

    public UserException(String code, Object[] args) {
        super("user", code, args, null);
    }

}