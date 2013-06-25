/*
 * generated by Xtext
 */
package org.lunifera.examples.ecview.dsl.formatting

import org.eclipse.xtext.formatting.impl.AbstractDeclarativeFormatter
import org.eclipse.xtext.formatting.impl.FormattingConfig
import org.lunifera.examples.ecview.dsl.services.MasterDetailDSLGrammarAccess
import org.lunifera.examples.ecview.dsl.services.MasterDetailDSLGrammarAccess$UiDetailPartElements
import org.lunifera.examples.ecview.dsl.services.MasterDetailDSLGrammarAccess$UiMasterDetailTilesElements
import org.lunifera.examples.ecview.dsl.services.MasterDetailDSLGrammarAccess$UiMasterPartElements
import org.lunifera.examples.ecview.dsl.services.MasterDetailDSLGrammarAccess$UiTableElements
import org.lunifera.examples.ecview.dsl.services.MasterDetailDSLGrammarAccess$UiTextFieldElements

// import com.google.inject.Inject;
// import org.lunifera.examples.ecview.dsl.services.MasterDetailDSLGrammarAccess
/**
 * This class contains custom formatting description.
 * 
 * see : http://www.eclipse.org/Xtext/documentation.html#formatting
 * on how and when to use it 
 * 
 * Also see link org.eclipse.xtext.xtext.XtextFormattingTokenSerializer as an example
 */
class MasterDetailDSLFormatter extends AbstractDeclarativeFormatter {
	override protected void configureFormatting(FormattingConfig c) {
		configure(config, getGrammarAccess() as MasterDetailDSLGrammarAccess);
	}

	def void configure(FormattingConfig c, MasterDetailDSLGrammarAccess ga) {

		c.setAutoLinewrap(120);

		configure(c, ga.getUiDetailPartAccess());
		configure(c, ga.getUiMasterDetailTilesAccess());
		configure(c, ga.getUiMasterPartAccess());

		configure(c, ga.getUiTextFieldAccess());
		configure(c, ga.getUiTableAccess());
	}

	def void configure(FormattingConfig config, UiDetailPartElements ele) {
		config.setLinewrap(1, 1, 2).around(ele.getRule());
		config.setLinewrap(1, 1, 2).after(ele.getLeftCurlyBracketKeyword_2());
		config.setLinewrap(1).before(ele.getSizeKeyword_3_0_0());
		config.setLinewrap(1).before(ele.getTextAlignKeyword_3_1_0());
		config.setLinewrap(1).before(ele.getRightCurlyBracketKeyword_5());

		// indentation
		config.setIndentationIncrement().after(ele.getLeftCurlyBracketKeyword_2());
		config.setIndentationDecrement().before(ele.getRightCurlyBracketKeyword_5());
	}

	def void configure(FormattingConfig config, UiMasterDetailTilesElements ele) {
		config.setLinewrap(1, 1, 2).around(ele.getRule());
		config.setLinewrap(1, 1, 2).after(ele.getLeftCurlyBracketKeyword_3());
		config.setLinewrap(1).before(ele.getRightCurlyBracketKeyword_6());

		// indentation
		config.setIndentationIncrement().after(ele.getLeftCurlyBracketKeyword_3());
		config.setIndentationDecrement().before(ele.getRightCurlyBracketKeyword_6());
	}

	def void configure(FormattingConfig config, UiMasterPartElements ele) {
		config.setLinewrap(1, 1, 2).around(ele.getRule());
		config.setLinewrap(1, 1, 2).after(ele.getLeftCurlyBracketKeyword_2());
		config.setLinewrap(1).before(ele.getRightCurlyBracketKeyword_4());

		// indentation
		config.setIndentationIncrement().after(ele.getLeftCurlyBracketKeyword_2());
		config.setIndentationDecrement().before(ele.getRightCurlyBracketKeyword_4());
	}

	def void configure(FormattingConfig config, UiTextFieldElements ele) {
		config.setLinewrap(1, 1, 2).around(ele.getRule());
	}

	def void configure(FormattingConfig config, UiTableElements ele) {
		config.setLinewrap(1, 1, 2).around(ele.getRule());
	}
}