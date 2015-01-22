package me.tunsi.test.asm;

import java.lang.reflect.Method;

import org.junit.Test;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class ClassTestCase {

	@Test
	public void newClassCase() throws Exception {
		ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
		
		classWriter.visit(Opcodes.V1_5, Opcodes.ACC_PUBLIC, "Test", null, "java/lang/Object", null);

		{
			/**
			 * 构造方法
			 */
			MethodVisitor methodVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);

			methodVisitor.visitCode();

			methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);// 0 表示当前对象
			methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
			methodVisitor.visitInsn(Opcodes.RETURN);
			methodVisitor.visitMaxs(0, 0);
			methodVisitor.visitEnd();
		}

		{
			/**
			 * 一个计算两个数之和的cal方法
			 */
			MethodVisitor methodVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "cal", "(II)I", null, null);
			methodVisitor.visitCode();

			methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
			methodVisitor.visitVarInsn(Opcodes.ILOAD, 2);
			methodVisitor.visitInsn(Opcodes.IADD);
			methodVisitor.visitInsn(Opcodes.IRETURN);
			methodVisitor.visitMaxs(2, 2);
			methodVisitor.visitEnd();
		}

		classWriter.visitEnd();

		/**
		 * 从classwrite 获得这个类的byte 数组。然后加载这个数组为一个class 利用反射调用方法
		 */

		final byte[] bs = classWriter.toByteArray();

		ClassLoader classLoader = new ClassLoader() {

			@Override
			protected Class<?> findClass(String name) throws ClassNotFoundException {
				return defineClass(name, bs, 0, bs.length);
			}

		};

		Class<?> class1 = classLoader.loadClass("Test");

		Method method = class1.getMethod("cal", int.class, int.class);

		System.out.println(method.invoke(class1.newInstance(), 16, 2));

		
		ClassWriter classWriter2 = new ClassWriter(ClassWriter.COMPUTE_MAXS);
		ClassReader classReader = new ClassReader("Test");
		ClassAdapter classAdapter = new ClassAdapter(classWriter2);
		classReader.accept(classAdapter, ClassReader.SKIP_DEBUG);
		
		
		final byte[] bs2 = classWriter2.toByteArray();
		ClassLoader classLoader2 = new ClassLoader() {

			@Override
			protected Class<?> findClass(String name) throws ClassNotFoundException {
				return defineClass(name, bs2, 0, bs2.length);
			}

		};
		
		Class<?> class2 = classLoader2.loadClass("Test");

		Method method2 = class2.getMethod("cal", int.class, int.class);
		System.out.println(method2.invoke(class2.newInstance(), 12, 4));
		
//		ClassWriter classWriter2 = new ClassWriter(ClassWriter.COMPUTE_MAXS);
//		
//		ClassReader classReader = new ClassReader("Test");
//		classReader.accept(classVisitor, ClassReader.SKIP_DEBUG);
//		
//		final byte[] bs2 = classWriter2.toByteArray();
//
//		ClassLoader classLoader2 = new ClassLoader() {
//
//			@Override
//			protected Class<?> findClass(String name) throws ClassNotFoundException {
//				return defineClass(name, bs2, 0, bs2.length);
//			}
//
//		};
//
//		Class<?> class2 = classLoader2.loadClass("Test");
//
//		Method method2 = class2.getMethod("cal", int.class, int.class);
//		System.out.println(method2.invoke(class2.newInstance(), 12, 4));
		
	}

	@Test
	public void newClassCase1() throws Exception {
		ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
		
		classWriter.newClass("Example");
		classWriter.newField("Example", "name", "Ljava/lang/String;");
		classWriter.newMethod("Example", "display", "", false);
	}
}
