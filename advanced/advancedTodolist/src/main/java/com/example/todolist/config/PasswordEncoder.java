package com.example.todolist.config;

import at.favre.lib.crypto.bcrypt.BCrypt;


public class PasswordEncoder {

    /**
     * BCrypt 알고리즘을 사용하여 패스워드를 암호화
     * @param rawPassword 할 패스워드
     * @return encodedPassword 해싱된 패스워드
     * withDefaults() 메소드는 BCrypt 인스턴스를 생성하고 hashToString(반복횟수, 비밀번호 char형 배열) 으로 암호화
     */
    public static String encode(String rawPassword) {
        return BCrypt.withDefaults().hashToString(BCrypt.MIN_COST, rawPassword.toCharArray());
    }

    /**
     * 비밀번호와 저장된 해시가 일치하는지 검증
     * @param rawPassword 입력된 비밀번호
     * @param encodedPassword 저장되어 있는 비밀번호
     * @return true(일치)/false(불일치)
     * verifyer() 메소드는 검증기 생성, verify()는 검증 진행
     */
    public static boolean matches(String rawPassword, String encodedPassword) {
        BCrypt.Result result = BCrypt.verifyer().verify(rawPassword.toCharArray(),encodedPassword);
        return result.verified;
    }

}
