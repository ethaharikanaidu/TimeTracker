package controller;


import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;
import view.PnlActivity;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class MouseClickMonitor implements NativeMouseInputListener {
    
    

    public void nativeMouseClicked(NativeMouseEvent e) {
        PnlActivity.getInstance().setCount();
//        System.out.println("Mouse Clicked: " + count);
//                System.out.println("Mouse Clicked: ");
    }

    public void nativeMousePressed(NativeMouseEvent e) {
//		System.out.println("Mouse Pressed: " + e.getButton());
    }

    public void nativeMouseReleased(NativeMouseEvent e) {
//		System.out.println("Mouse Released: " + e.getButton());
    }

    public void nativeMouseMoved(NativeMouseEvent e) {
//		System.out.println("Mouse Moved: " + e.getX() + ", " + e.getY());
    }

    public void nativeMouseDragged(NativeMouseEvent e) {
//		System.out.println("Mouse Dragged: " + e.getX() + ", " + e.getY());
    }

//    public static void main(String[] args) {
//        try {
//            GlobalScreen.registerNativeHook();
//        } catch (NativeHookException ex) {
////			System.err.println("There was a problem registering the native hook.");
////			System.err.println(ex.getMessage());
//
//            System.exit(1);
//        }
//
//        // Construct the example object.
//        MouseClickMonitor example = new MouseClickMonitor();
//
//        // Add the appropriate listeners.
//        GlobalScreen.addNativeMouseListener(example);
//        GlobalScreen.addNativeMouseMotionListener(example);
//    }
}
