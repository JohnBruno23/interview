package com.doc.ai.controller;

/*-----------------------------------------------------------------------
 * Coded By : John
 * Date : 17/11/2026
 * Purpose : Chat Controller for Question and answer 
 *-----------------------------------------------------------------------*/

/* All the Covered Questions 
 * ------------All the AI questions are tested --------------------

---working hours---
What are the official working hours?
How many hours should an employee work per day?
What are the working days in a week?

---Leave Policy--
How many casual leaves are allowed per year?
How many sick leaves can an employee take annually?
What is the earned leave entitlement?
Do I need a medical certificate for sick leave?
When is a medical certificate required?

----Work From Home----
Can employees work from home?
How many days per week can I work from home?
Is manager approval required for work from home?


----Travel Policy----
What travel expenses are reimbursable?
Can meal expenses be claimed during business travel?
Are receipts required for reimbursement?

----Performance Review----
When are performance reviews conducted?
How frequently are performance reviews held?

----Health Insurance----
Does the company provide health insurance?
Are dependents covered under company health insurance?


-----Probation Policy-----
How long is the probation period for new employees?
When does the probation period start?

-----Resignation Policy-----
What is the notice period for resignation?
How many days' notice must an employee provide before leaving?


----Code of Conduct----
What are the employee code of conduct requirements?
Are employees required to protect confidential information?


-------Training Policy-------
What mandatory training must employees complete?
How often is security training required?

------Semantic Search Questions (Good for Vector Databases)------
Can I work remotely?
How much vacation time do I get?
What happens if I am sick for several days?
What benefits does the company offer?
What should I do before leaving the company?
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.doc.ai.service.GeminiService;
import com.doc.ai.service.RagService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    // private final RagService ragService;
    
    @Autowired
	 private RagService ragService;

    @PostMapping("/ask")
    public String ask(
            @RequestParam String question) {

        return ragService.askQuestion(question);
    }
}