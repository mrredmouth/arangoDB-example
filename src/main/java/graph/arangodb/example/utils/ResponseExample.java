/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph.arangodb.example.utils;

import com.arangodb.CursorResult;
import graph.arangodb.example.bean.ResponseBean;

/**
 *
 * @author ranjeet
 */
public class ResponseExample {

    private CursorResult<ResponseBean> result;

    public ResponseExample(CursorResult<ResponseBean> result) {
        this.result = result;
    }

    public CursorResult<ResponseBean> getResult() {
        return result;
    }
}