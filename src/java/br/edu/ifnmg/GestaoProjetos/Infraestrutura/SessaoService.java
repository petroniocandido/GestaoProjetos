/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Infraestrutura;

import java.io.Serializable;
import java.util.HashMap;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author petronio
 */
@SessionScoped
public class SessaoService implements Serializable  {

    public void put(String key, String value) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Cookie ck = new Cookie(key, value);
        ck.setMaxAge(-1);
        ((HttpServletResponse) ctx.getExternalContext().getResponse()).addCookie(ck);
        cookies.put(key, ck);
    }

    HashMap<String, Cookie> cookies = new HashMap<>();

    private Cookie getCookie(String key) {
        if (cookies.isEmpty()) {
            FacesContext ctx = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) ctx.getExternalContext().getRequest();
            Cookie[] tmp = request.getCookies();

            for (Cookie cookie : tmp) {
                cookies.put(cookie.getName(), cookie);
            }
        }

        if (cookies.containsKey(key)) {
            return cookies.get(key);
        } else {
            return null;
        }
    }

    public String get(String key) {
        Cookie tmp = getCookie(key);
        if (tmp != null) {
            return tmp.getValue();
        }
        return null;
    }

    public void delete(String key) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Cookie cookie = getCookie(key);
        
        cookies.remove(key);

        if (cookie != null) {
            cookie.setMaxAge(0);
            cookie.setValue(null);
            ((HttpServletResponse) ctx.getExternalContext().getResponse()).addCookie(cookie);
        }

    }

    public void limpar() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) ctx.getExternalContext().getRequest();
        Cookie[] cks = request.getCookies();

        for (Cookie cookie : cks) {
            cookie.setMaxAge(0);
            cookie.setValue(null);
            ((HttpServletResponse) ctx.getExternalContext().getResponse()).addCookie(cookie);

        }
    }
}
