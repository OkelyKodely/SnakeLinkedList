/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.linkedlist;

/**
 *
 * @author d
 */
public class LinkedList {

    Node node[] = new Node[3];

    class Node{

        Node node;
        Object obj;
    }

    class Cursor {
        Node _;
    }

    public LinkedList() {
        constructNodeData();
        constructNodeLinks();
        traverseLinkedList();
    }

    public void constructNodeData() {
        for(int i=0; i<node.length; i++) {
            node[i] = new Node();
        }

        node[0].obj = "a";
        node[1].obj = "b";
        node[2].obj = "c";
    }

    public void constructNodeLinks() {
        for(int i=0; i<node.length; i++) {
            if(i < node.length - 1) {
                node[i].node = node[i+1];
            }
        }
    }

    public void traverseLinkedList() {
        Cursor cursor = new Cursor();
        int i = 1;
        for(cursor._ = node[0]; cursor._ != null; cursor._ = cursor._.node) {
            println(i, cursor);
        }
    }

    public void println(int i, Cursor cursor) {
        System.out.println("Node" + i + ": " + cursor._.obj.toString());
    }

    public static void main(String[] args) {
        LinkedList ll = new LinkedList();
    }
}
