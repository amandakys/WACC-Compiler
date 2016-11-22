.data 

msg_0: 
	.word 7
	.ascii "correct"

msg_1: 
	.word 6
	.ascii "%.*s\0"

msg_2: 
	.word 1
	.ascii  

msg_3: 
	.word 9
	.ascii "incorrect"

msg_4: 
	.word 6
	.ascii "%.*s\0"

msg_5: 
	.word 1
	.ascii  

.text 

.global main 
main: 
	PUSH {lr}
	SUB sp, sp, #4
	LDR r4, =13
	STRB r5, [sp]
	ADD sp, sp, #4
	LDR r6, =13
	CMP r6, r6
	MOVEQ r6, r6
	MOVNE r6, r6
	CMP r7, #0
	BEQ L0
	LDR r7, =msg_0
	MOV r0, r7
	BL p_print_string
	BL p_print_ln
	B L1
L0: 
	LDR r4, =msg_3
	MOV r0, r4
	BL p_print_string
	BL p_print_ln
	LDR r0, =0
	POP {pc}
	.ltorg 
p_print_string: 
	PUSH {lr}
	LDR r1, [r0]
	ADD r2, r0, #4
	LDR r0, =msg_1
	ADD r0, r0, #4
	BL printf
	MOV r0, #0
	BL fflush
	POP {pc}
p_print_ln: 
	PUSH {lr}
	LDR r0, =msg_2
	ADD r0, r0, #4
	BL puts
	MOV r0, #0
	BL fflush
	POP {pc}
p_print_string: 
	PUSH {lr}
	LDR r1, [r0]
	ADD r2, r0, #4
	LDR r0, =msg_4
	ADD r0, r0, #4
	BL printf
	MOV r0, #0
	BL fflush
	POP {pc}
p_print_ln: 
	PUSH {lr}
	LDR r0, =msg_5
	ADD r0, r0, #4
	BL puts
	MOV r0, #0
	BL fflush
	POP {pc}
