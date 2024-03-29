/*
	Copyright (c) 2009 Olivier Chafik, All Rights Reserved
	
	This file is part of JNAerator (http://jnaerator.googlecode.com/).
	
	JNAerator is free software: you can redistribute it and/or modify
	it under the terms of the GNU General Public License as published by
	the Free Software Foundation, either version 3 of the License, or
	(at your option) any later version.
	
	JNAerator is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	GNU General Public License for more details.
	
	You should have received a copy of the GNU General Public License
	along with JNAerator.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.ochafik.lang.jnaerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import junit.framework.Assert;

import org.anarres.cpp.LexerException;
import org.antlr.runtime.RecognitionException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;

import com.ochafik.io.ReadText;
import com.ochafik.junit.ParameterizedWithDescription;
import com.ochafik.lang.compiler.CompilerUtils;
import com.ochafik.lang.compiler.MemoryJavaFile;
import com.ochafik.lang.jnaerator.JNAerator.Feedback;
import com.ochafik.lang.jnaerator.studio.JNAeratorStudio.SyntaxException;
import com.ochafik.net.URLUtils;
import com.ochafik.util.listenable.Adapter;
import com.ochafik.util.listenable.Filter;
import com.ochafik.util.string.RegexUtils;
import com.ochafik.util.string.StringUtils;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class JNAerationTest {
	String title;
	TestDesc test;
	public JNAerationTest(String title, TestDesc test) {
		this.title = title;
		this.test = test;
	}
	
	static class TestDesc {
		String cSource;
		String libraryName = "test";
		JNAeratorConfig.Runtime runtime;
		Map<String, String> extraJavaSourceFilesContents = new HashMap<String, String>();
		public TestDesc(String cSource, JNAeratorConfig.Runtime runtime) {
			this.cSource = cSource;
			this.runtime = runtime;
		}
		public TestDesc addSource(String className, String javaContent) {
			extraJavaSourceFilesContents.put(className, javaContent);
			return this;
		}
		public TestDesc addMainContentSource(String className, String javaContent) {
			Set<String> imports = new TreeSet<String>();
			List<String> content = new ArrayList<String>();
			//String lib = libraryName + "." + StringUtils.capitalize(libraryName) + "Library";
			//imports.add("import " + lib + ";");
			//imports.add("import static " + lib + ".*;");
			switch (runtime) {
				
			case JNAerator:
			case JNA:
				imports.add("import com.sun.jna.*;");
				imports.add("import com.sun.jna.ptr.*;");
				imports.add("import java.nio.*;");
				break;
			case BridJ:
				imports.add("import org.bridj.*;");
				imports.add("import org.bridj.cpp.*;");
				imports.add("import java.nio.*;");
				break;
			}

			for (String line : javaContent.split("\r?\n")) {
				if (line.matches("^\\s*import[^\\w].*"))
					imports.add(line);
				else
					content.add(line);
			}
			String simpleName = new File(className.replace('.', File.separatorChar)).getName();
			String transformedContent = StringUtils.implode(imports, "\n") + "\n" +
				"public class " + simpleName + " {\n" +
				"\tpublic static void main(String[] args) {\n\t\t" + 
				StringUtils.implode(content, "\n\t\t").trim() + "\n" +
				"\t}\n}";
//			System.out.println(transformedContent);
			extraJavaSourceFilesContents.put(className.replace('.', '/') + ".java", transformedContent);
			return this;
		}
	}
	
	@Test
	public void test() throws SyntaxException, IOException, LexerException, RecognitionException {
		JNAeratorConfig config = new JNAeratorConfig();
		config.defaultLibrary = test.libraryName;
		config.compile = true;
		config.runtime = test.runtime;
		config.preferJavac = true;
		config.extraJavaSourceFilesContents.putAll(test.extraJavaSourceFilesContents);
		config.libraryForElementsInNullFile = test.libraryName;//test.classNameToJavaContent;
//		config.addFile(getFile(), "");
		config.preprocessorConfig.includeStrings.add(test.cSource);
		//config.
		 
		new JNAerator(config).jnaerate(new Feedback() {
			
			@Override
			public void sourcesParsed(SourceFiles sourceFiles) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setStatus(String string) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setFinished(Throwable ex) {
                if (ex != null) {
                    ex.printStackTrace(System.out);
                }
				Collection<Entry<String, String>> cc = test.extraJavaSourceFilesContents.entrySet();//new CompoundCollection<Map.Entry<String, String>>(test.extraJavaSourceFilesContents.entrySet(), Arrays.asList(new Pair<String, String>("?.java", test.cSource)));
				System.out.println("Finished with an error ! " + ex);
				if (ex instanceof CompilerUtils.CompilationError) {
					CompilerUtils.CompilationError cex = (CompilerUtils.CompilationError)ex;
					for (Map.Entry<String, MemoryJavaFile> e : cex.inputs.entrySet()) {
						System.out.println("\n" + e.getKey() + ":");
						System.out.println(RegexUtils.regexReplace(Pattern.compile("\n"), "\n" + e.getValue().getCharContent(true), new Adapter<String[], String>() {
							int line = 0;

							@Override
							public String adapt(String[] value) {
								return "\n" + (++line) + ":\t\t";
							}
						}));
					}
				}
				/*
				for (final Map.Entry<String, String> e : cc) {
					System.out.println("\n" + e.getKey() + ":");
					System.out.println(RegexUtils.regexReplace(Pattern.compile("\n"), "\n" + e.getValue(), new Adapter<String[], String>() {
						int line = 0;

						@Override
						public String adapt(String[] value) {
							return "\n" + (++line) + ":\t\t";
						}
					}));
				}*/
				Assert.assertTrue("Error : " + ex + " in :\n" + test.extraJavaSourceFilesContents, false);
//				throw new RuntimeException(e);
			}
			
			@Override
			public void setFinished(File toOpen) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void wrappersGenerated(Result result) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	static Pattern runtimePattern = Pattern.compile("(?m)#runtime\\((BridJ|JNAerator|JNA)\\).*");
	@Parameters
	public static List<Object[]> readParameters() throws IOException {
		//List<String> lines = ReadText.readLines(ObjCppParsingTests.class.getClassLoader().getResource(TEST_FILE));
		//List<String> lines = ReadText.readLines(TEST_FILE);
		//TestOption testOption = TestOption.ParseAndPrettyPrint;
		//List<URL> list = URLUtils.listFiles(new URL("jar:file:/Users/ochafik/Prog/Java/bin/jnaerator.jar!/com/ochafik/lang/jnaerator"), null);
		List<Object[]> data = new ArrayList<Object[]>();
		
		URL dir = JNAerationTest.class.getClassLoader().getResource("com/ochafik/lang/jnaerator/tests");
		if(dir == null)
			throw new FileNotFoundException("Could not find test files !");
 		for (URL testURL : URLUtils.listFiles(dir, new Filter<String>() {
			@Override
			public boolean accept(String path) {
				return path.toLowerCase().endsWith(".test");// && path.contains("objective");
			}
		})) {
			String t = ReadText.readText(testURL);
			String[] tt = t.split("\n--\n");
			String cSource = tt[0];
			String n = new File(URLDecoder.decode(testURL.toString(), "utf-8")).getName();
			for (int i = 1; i < tt.length; i++) {
				String rawSource = tt[i];
				JNAeratorConfig.Runtime runtime = JNAeratorConfig.Runtime.JNAerator;
				Matcher m = runtimePattern.matcher(rawSource);
				if (m.find()) {
					runtime = Enum.valueOf(JNAeratorConfig.Runtime.class, m.group(1).trim());	
				}
				String javaSource = rawSource.replaceAll("^#", "//#");
				String testName = n + " / " + runtime.name();
				data.add(new Object[] { 
						testName, 
						new TestDesc(cSource, runtime).addMainContentSource(
							"Test" + runtime.name(),
							"// " + testName + "\n" + javaSource
						) 
				});
			}
		}
		return data;
	}

}
