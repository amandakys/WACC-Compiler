.text 

.global main 
main: 
	PUSH {lr}
	SUBnull sp, sp, #4
	LDR r4, =13
	STRB r5, [sp]
	ADD sp, sp, #4
	LDR r6, =13
CMP r6, r6
	LDR r0, =0
	POP {pc}
	.ltorg 
