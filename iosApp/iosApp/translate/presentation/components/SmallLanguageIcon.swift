//
//  SmallLanguageIcon.swift
//  iosApp
//
//  Created by Betuel on 26.04.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct SmallLanguageIcon: View {
    var language: UiLanguage
    var body: some View {
        Image(uiImage: UIImage(named: language.imageName.lowercased())!)
            .resizable()
            .frame(width: 30, height: 30)
    }
}

#Preview {
    SmallLanguageIcon(language: UiLanguage(language: .romanian, imageName: "romanian"))
}
