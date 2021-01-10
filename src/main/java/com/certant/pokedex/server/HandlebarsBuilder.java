package com.certant.pokedex.server;

import spark.template.handlebars.HandlebarsTemplateEngine;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.HumanizeHelper;
import com.github.jknack.handlebars.helper.I18nHelper;
import com.github.jknack.handlebars.helper.NumberHelper;
import com.github.jknack.handlebars.helper.StringHelpers;

public class HandlebarsBuilder {

	private HandlebarsTemplateEngine engine;

	private HandlebarsBuilder(HandlebarsTemplateEngine engine) {
		this.engine = engine;
	}

	public static HandlebarsBuilder create() {
		return new HandlebarsBuilder(
				new HandlebarsTemplateEngine());
	}

	public HandlebarsBuilder withHelper(String name,
			Helper<?> helper) {
		getHandlerbars().registerHelper(name, helper);
		return this;
	}

	private Handlebars getHandlerbars() {
		return (Handlebars) ReflectionUtils.getField(this.engine, "handlebars");
	}

	public HandlebarsTemplateEngine build() {
		return engine;
	}

	public HandlebarsBuilder withDefaultHelpers() {
		StringHelpers.register(getHandlerbars());
		NumberHelper.register(getHandlerbars());
		HumanizeHelper.register(getHandlerbars());
		withHelper("i18n", I18nHelper.i18n);
		return this;
	}
}
