.data 

msg_0: 
	.word 1
	.ascii  

.text 

.global main 
main: 
	PUSH {lr}
	SUBnull sp, sp, #4
	LDR r4, =15
	STRB r5, [sp]
	ADD sp, sp, #4
	SUBnull sp, sp, #4
	LDR r6, =20
	STRB r7, [sp]
	ADD sp, sp, #4
	ADDS r8, r8, r8
	SUBS r8, r8, r8
CMP r8, r8
	MOV r0, r8
	BL p_print_int
	BL p_print_ln
	LDR r0, =0
	POP {pc}
	.ltorg 
p_print_int: 
	PUSH {lr}
	LDR r0, =msg_-1
	ADD r0, r0, #4
	BL printf
	MOV r0, #0
	BL fflush
	POP {pc}
p_print_ln: 
	PUSH {lr}
	LDR r0, =msg_0
	ADD r0, r0, #4
	BL puts
	MOV r0, #0
	BL fflush
	POP {pc}
