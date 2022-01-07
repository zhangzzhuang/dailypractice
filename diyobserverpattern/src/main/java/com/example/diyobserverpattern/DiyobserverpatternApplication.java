package com.example.diyobserverpattern;

import com.example.diyobserverpattern.observer.BasiObserver;
import com.example.diyobserverpattern.observer.DiyObserver;
import com.example.diyobserverpattern.observer.ManiacObserver;
import com.example.diyobserverpattern.subject.BasiSubject;
import com.example.diyobserverpattern.subject.DiySubject;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class DiyobserverpatternApplication {


	public static void main(String[] args) {

		DiySubject subject = new BasiSubject();

		DiyObserver basiObserver = new BasiObserver();
		DiyObserver maniacObserver = new ManiacObserver();

		subject.attach(basiObserver);
		subject.attach(maniacObserver);
		subject.notifyChanged();
	}

}
