package org.spirit.assembled.transaction.tcc.gateway.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @description 类描述
 * @author qiudequan
 * @createTime 2017年1月24日 下午1:13:48 
 */
@Controller
@RequestMapping(value = "/gateway/transaction")
public class GatewayController {
  static final Logger LOG = LoggerFactory.getLogger(GatewayController.class);
  
  @RequestMapping(value = "/manage", method = RequestMethod.GET)
  public ModelAndView manage() {
    return new ModelAndView("transaction-manage");
  }
  
}
